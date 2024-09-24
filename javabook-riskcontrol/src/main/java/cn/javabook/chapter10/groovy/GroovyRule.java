package cn.javabook.chapter10.groovy;

import org.apache.flink.cep.pattern.Pattern;

/**
 * Groovy脚本接口
 *
 */
public interface GroovyRule<T> {
    /**
     * 返回 Flink-Cep Pattern结构体
     *
     */
    Pattern<T, ?> getPattern();
}
