package cn.javabook.chapter10.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Groovy Shell每一次执行时代码时会动态将代码编译成Java Class
 * 缺点：性能较差
 *
 */
public class GroovyShellDemo {
    public static void main(String[] args) {
        // groovy脚本内容
        String groovy_script = "println 'groovy'; println 'name = ' + name;";
        // 初始化Binding
        Binding binding = new Binding();
        // 使用setVariable
        binding.setVariable("name", "javabook");
        // 创建脚本对象
        GroovyShell shell = new GroovyShell(binding);
        // 执行脚本
        Object variableRes = shell.evaluate(groovy_script);
    }
}
