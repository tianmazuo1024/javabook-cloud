package cn.javabook.chapter10.groovy;

import com.googlecode.aviator.AviatorEvaluator;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import java.io.Serializable;

/**
 * 单次登录失败的条件表达式检测
 *
 */
public class SingletonLoginFailureCondition<T> extends SimpleCondition<T> implements Serializable {
    /**
     * Aviator字段
     */
    private String field;

    /**
     * 规则表达式
     */
    private String expression;

    public SingletonLoginFailureCondition(String field, String expression) {
        this.field = field;
        this.expression = expression;
        // 加载Aviator自定义函数
        AviatorEvaluator.addFunction(new SumFunction(this.field));
    }

    /**
     * 自定义where条件未做任何过滤
     *
     */
    @Override
    public boolean filter(T eventPO) throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        params.put("data", eventPO.getEventName());
//        // Aviator表达式计算
//        return (Boolean) AviatorEvaluator.execute(expression, params);
        return true;
    }
}
