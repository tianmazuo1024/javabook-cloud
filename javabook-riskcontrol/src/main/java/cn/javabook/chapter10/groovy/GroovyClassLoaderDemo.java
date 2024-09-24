package cn.javabook.chapter10.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.File;
import java.io.IOException;

/**
 * Java通过类加载器调用Groovy自定义类
 *
 */
public class GroovyClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        // 当前线程的类加载器
        GroovyClassLoader loader = new GroovyClassLoader(
                // 当前线程的类加载器
                Thread.currentThread().getContextClassLoader(),
                new CompilerConfiguration()
        );
        // groovy脚本路径
        File file = new File("文件路径");
        // 通过GroovyClassLoader加载类
        Class<?> groovyClass = loader.parseClass(file);
        // 获取ScriptEngine对象的实例
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        // 通过invokeMethod调用实例方法
        groovyObject.invokeMethod("print", null);
    }
}
