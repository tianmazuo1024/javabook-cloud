package cn.javabook.chapter10.groovy;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import java.io.IOException;

/**
 * GroovyScriptEngine从指定的位置加载Groovy脚本
 *
 */
public class GroovyScriptEngineDemo {
    public static void main(String[] args) throws IOException, ScriptException, ResourceException {
        // 初始化GroovyScriptEngine
        GroovyScriptEngine engine = new GroovyScriptEngine("/Users/bear/home/work/");
        // 初始化Binding
        Binding binding = new Binding();
        // 执行脚本
        engine.run("wowcount.groovy", binding);
    }
}
