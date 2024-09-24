package cn.javabook.chapter10;

import cn.javabook.chapter10.entity.SimpleCEPObject;
import cn.javabook.chapter10.entity.SimpleEvent;
import cn.javabook.chapter10.utils.GroovyUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * 测试Flink-CEP
 *
 */
public class TestFlinkCEP {
    /**
     * 公共方法: 添加水印和分组
     *
     */
    private KeyedStream<SimpleEvent, Integer> getSteam(DataStream<SimpleEvent> dataStream) {
        return dataStream
                // 生成水印
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy
                                // 最大数据延迟
                                .<SimpleEvent>forBoundedOutOfOrderness(Duration.ZERO)
                                // 给每个事件元素添加时间戳
                                .withTimestampAssigner(new SerializableTimestampAssigner<SimpleEvent>() {
                                    @Override
                                    public long extractTimestamp(SimpleEvent event, long timestamp) {
                                        return event.getEventTime();
                                    }
                                }))
                // 根据userId分组
                .keyBy(SimpleEvent::getUserId);
    }

    /**
     * 循环模式（非连续）
     *
     */
    @DisplayName("Flink-CEP的循环模式（非连续）：检测登录失败超过3次的用户")
    @Test
    public void testLoginFailure() throws Exception {
        // 流式计算上下文环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 模拟事件流并分组
        DataStream<SimpleEvent> dataStream = environment.fromElements(
                new SimpleEvent(1, "fail", 1000L),
                new SimpleEvent(2, "fail", 2000L),
                new SimpleEvent(1, "fail", 3000L),
                new SimpleEvent(2, "fail", 4000L),
                new SimpleEvent(1, "fail", 5000L),
                new SimpleEvent(2, "fail", 6000L),
                new SimpleEvent(2, "fail", 7000L),
                new SimpleEvent(3, "fail", 8000L)
        );
        KeyedStream<SimpleEvent, Integer> stream = getSteam(dataStream);
        // 定义Pattern。不管什么了类型的模式，都是以Pattern.begin()方法开头的
        Pattern<SimpleEvent, SimpleEvent> pattern = Pattern.<SimpleEvent>begin("cep-login-fail")
            .where(new SimpleCondition<SimpleEvent>() {
                @Override
                public boolean filter(SimpleEvent value) throws Exception {
                    return value.getEventName().equals("fail");
                }
            })
            // 失败3次或3次以上，timesOrMore就是阈值
            .timesOrMore(3);
        // 将Pattern应用到事件流
        PatternStream<SimpleEvent> patternStream = CEP.pattern(stream, pattern);
        // 提取匹配事件
        patternStream.process(new PatternProcessFunction<SimpleEvent, String>() {
            @Override
            public void processMatch(Map<String, List<SimpleEvent>> map, Context context, Collector<String> output) {
                // 提取三次登录失败事件
                List<SimpleEvent> list = map.get("cep-login-fail");
                StringBuilder builder = null;
                for (int i = 0; i < list.size(); i++) {
                    if (0 == i) {
                        builder = new StringBuilder("uid:" + list.get(i).getUserId() +
                                                    " 登录失败时间: " + list.get(i).getEventTime());
                    } else {
                        builder.append(", ").append(list.get(i).getEventTime());
                    }
                }
                assert builder != null;
                output.collect(builder.toString());
            }
        })
        .print("result");
        // 最终执行，这行代码类似于流式计算中的终端操作，没有它，上面什么都不会做
        environment.execute();
    }

    /**
     * 循环模式（连续）
     *
     */
    @DisplayName("Flink-CEP的循环模式（连续）：检测连续事件的用户")
    @Test
    public void testLoginConsecutiveFailure() throws Exception {
        // 流式计算上下文环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 模拟事件流并分组
        DataStream<SimpleEvent> dataStream = environment.fromElements(
                new SimpleEvent(1, "fail", 1000L),
                new SimpleEvent(1, "fail", 2000L),
                new SimpleEvent(1, "register", 3000L),
                new SimpleEvent(1, "fail", 4000L),
                new SimpleEvent(1, "success", 5000L),
                new SimpleEvent(2, "fail", 6000L),
                new SimpleEvent(2, "fail", 7000L),
                new SimpleEvent(3, "success", 8000L)
        );
        KeyedStream<SimpleEvent, Integer> stream = getSteam(dataStream);
        // 定义Pattern
        Pattern<SimpleEvent, SimpleEvent> pattern = Pattern
            .<SimpleEvent>begin("cep-login-fail-consecutive")
            .where(new SimpleCondition<SimpleEvent>() {
                @Override
                public boolean filter(SimpleEvent value) throws Exception {
                    return value.getEventName().equals("fail");
                }
            })
            // 连续3次，times就是阈值
            .times(3)
            // 严格紧邻（连续事件）
            .consecutive();
        // 将Pattern应用到事件流
        PatternStream<SimpleEvent> patternStream = CEP.pattern(stream, pattern);
        // 提取匹配事件
        patternStream.process(new PatternProcessFunction<SimpleEvent, String>() {
            @Override
            public void processMatch(Map<String, List<SimpleEvent>> map, Context context, Collector<String> output) {
                // 提取事件
                List<SimpleEvent> event = map.get("cep-login-fail-consecutive");
                SimpleCEPObject warn = new SimpleCEPObject(event, "连续登录失败");
                output.collect(warn.toString());
            }
        })
        .print("result");
        // 最终执行，这行代码类似于流式计算中的终端操作，没有它，上面什么都不会做
        environment.execute();
    }

    /**
     *  组合模式（连续）
     *
     */
    @DisplayName("Flink-CEP的组合模式（连续）：检测连续事件的用户")
    @Test
    public void testLoginConsecutiveFailureWithComposite() throws Exception {
        // 流式计算上下文环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 模拟事件流并分组
        DataStream<SimpleEvent> dataStream = environment.fromElements(
                new SimpleEvent(1, "fail", 2000L),
                new SimpleEvent(1, "register", 3000L),
                new SimpleEvent(2, "fail", 4000L),
                new SimpleEvent(1, "success", 5000L),
                new SimpleEvent(2, "fail", 6000L),
                new SimpleEvent(2, "success", 7000L),
                new SimpleEvent(3, "success", 8000L)
        );
        KeyedStream<SimpleEvent, Integer> stream = getSteam(dataStream);
        // 定义Pattern
        Pattern<SimpleEvent, SimpleEvent> pattern = Pattern
            .<SimpleEvent>begin("cep-login-first")
            .where(new SimpleCondition<SimpleEvent>() {
                @Override
                public boolean filter(SimpleEvent value) throws Exception {
                    return value.getEventName().equals("fail");
                }
            })
            /*
             * 如果是.consecutive()严格紧邻 (连续事件)，则uid2会出现这样的结果
             *
             * warning> 规则：登录失败 事件1 uid:2, 时间:6000, 行为:fail。
             * warning> 规则：登录成功 事件1 uid:2, 时间:7000, 行为:success。
             *
             */
            // 通过next()方法组合其他模式
            .next("cep-login-second")
            /*
             * 如果是.followedBy("cep-login-second")松散紧邻 (非连续事件)，则uid2会出现这样的结果
             *
             * warning> 规则：登录失败 事件1 uid:2, 时间:4000, 行为:fail
             * warning> 规则：登录成功 事件1 uid:2, 时间:7000, 行为:success
             * warning> 规则：登录失败 事件1 uid:2, 时间:6000, 行为:fail
             * warning> 规则：登录成功 事件1 uid:2, 时间:7000, 行为:success
             *
             */
            .where(new SimpleCondition<SimpleEvent>() {
                @Override
                public boolean filter(SimpleEvent value) throws Exception {
                    return value.getEventName().equals("success");
                }
            })
            // 时间限定在10秒之内
            .within(Time.seconds(10));
        // 将Pattern应用到事件流
        PatternStream<SimpleEvent> patternStream = CEP.pattern(stream, pattern);
        // 提取匹配事件
        patternStream.process(new PatternProcessFunction<SimpleEvent, String>() {
            @Override
            public void processMatch(Map<String, List<SimpleEvent>> map, Context context, Collector<String> output) {
                // 提取事件
                List<SimpleEvent> first = map.get("cep-login-first");
                List<SimpleEvent> second = map.get("cep-login-second");
                SimpleCEPObject warn1 = new SimpleCEPObject(first, "登录失败");
                SimpleCEPObject warn2 = new SimpleCEPObject(second, "登录成功");
                output.collect(warn1.toString());
                output.collect(warn2.toString());
            }
        })
        .print("result");
        // 最终执行，这行代码类似于流式计算中的终端操作，没有它，上面什么都不会做
        environment.execute();
    }

    /**
     * 通过反射方式执行Groovy脚本中的Pattern
     *
     */
    @DisplayName("通过反射方式执行Groovy脚本中的Pattern")
    @Test
    public void testLoginFailureByGroovy() throws Exception {
        // 流式计算上下文环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 模拟事件流并分组
        DataStream<SimpleEvent> dataStream = environment.fromElements(
                new SimpleEvent(1, "fail", 2000L),
                new SimpleEvent(1, "fail", 3000L),
                new SimpleEvent(2, "fail", 4000L),
                new SimpleEvent(1, "fail", 5000L),
                new SimpleEvent(2, "fail", 7000L),
                new SimpleEvent(2, "fail", 8000L),
                new SimpleEvent(2, "success", 6000L)
        );
        KeyedStream<SimpleEvent, Integer> stream = getSteam(dataStream);
        // Groovy脚本名称
        String clazz = "TestCEPPattern";
        // 反射执行的脚本方法
        String method = "getPattern";
        Pattern<SimpleEvent, SimpleEvent> pattern = (Pattern<SimpleEvent, SimpleEvent>) GroovyUtil.executeGroovyScript(clazz, method, null);
        PatternStream<SimpleEvent> patternStream = CEP.pattern(stream, pattern);
        // 匹配事件提取
        patternStream.process(
            new PatternProcessFunction<SimpleEvent, String>() {
                @Override
                public void processMatch(Map<String, List<SimpleEvent>> map, Context context, Collector<String> output) {
                    List<SimpleEvent> event = map.get("fail");
                    SimpleCEPObject warn = new SimpleCEPObject(event, "从Groovy加载Pattern");
                    output.collect(warn.toString());
                }
            })
        .print("result");
        // 最终执行，这行代码类似于流式计算中的终端操作，没有它，上面什么都不会做
        environment.execute();
    }
}
