package scripts.cep

import cn.javabook.chapter10.entity.SimpleEvent
import cn.javabook.chapter10.groovy.GroovyRule
import cn.javabook.chapter10.groovy.SingletonLoginFailureCondition
import org.apache.flink.cep.pattern.Pattern
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * Groovy脚本加载Pattern
 *
 */
class TestCEPPattern implements GroovyRule<SimpleEvent> {
    @Override
    Pattern getPattern() {
        return Pattern.<SimpleEvent>begin("fail")
                      // 自定义单次登录失败的条件
                      .where(new SingletonLoginFailureCondition<SimpleEvent>("FIELD","EXPRESSION"))
                      // 失败次数
                      .times(3)
                      // 时间限制
                      .within(Time.seconds(60))
    }
}
