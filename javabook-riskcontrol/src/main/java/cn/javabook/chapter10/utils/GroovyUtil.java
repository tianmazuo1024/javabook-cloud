package cn.javabook.chapter10.utils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Groovy脚本工具类
 *
 */
public class GroovyUtil {
    private static final ConcurrentHashMap<String, Class<Script>> clazzMaps = new ConcurrentHashMap<String, Class<Script>>();

    /**
     * 解析groovy脚本
     *
     */
    public static Object executeGroovyScript(String groovyClass, String method, Object args) {
        Object obj = null;
        File file = getClassByFile(groovyClass);
        String md5 = fingerKey(fileToString(file));
        /*
         * groovy类加载器
         *
         */
        GroovyClassLoader LOADER = getEngineByClassLoader(md5);
        try {
            // 解析脚本
            Class<?> groovyScript = LOADER.parseClass(file);
            clazzMaps.put(md5, (Class<Script>) groovyScript);
            // 获得实例
            GroovyObject groovyObject = (GroovyObject) groovyScript.newInstance();
            // 反射调用方法
            obj = groovyObject.invokeMethod(method, args);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CompilerConfiguration getCompilerConfiguration() {
        return new CompilerConfiguration();
    }

    /**
     * 生成groovy类加载器
     *
     */
    private static GroovyClassLoader getEngineByClassLoader(String key) {
        // 此代码有bug，如果script不为null，就会返回一个空的GroovyClassLoader
        GroovyClassLoader groovyClassLoader = null;
        Class<Script> script = clazzMaps.get(key);
        // 为每个groovy脚本都单独创建一个GroovyClassLoader对象
        if (script == null) {
            synchronized (key.intern()) {
                // Double Check
                script = clazzMaps.get(key);
                if (script == null) {
                    groovyClassLoader = new GroovyClassLoader(
                            // GroovyClassLoader的父ClassLoader为当前线程的加载器
                            Thread.currentThread().getContextClassLoader(),
                            getCompilerConfiguration()
                    );
                }
            }
        }
        return groovyClassLoader;
    }

    private static File getClassByFile(String groovyClass) {
        String path = "src/test/groovy/scripts/cep/" + groovyClass + ".groovy";
        return new File(path);
    }

    /**
     * 为每一个groovy脚本文件生成md5指纹
     *
     */
    private static String fingerKey(String scriptText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(scriptText.getBytes(StandardCharsets.UTF_8));
            final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
            StringBuilder ret = new StringBuilder(bytes.length * 2);
            for (byte aByte : bytes) {
                ret.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
                ret.append(HEX_DIGITS[aByte & 0x0f]);
            }
            return ret.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * File转String
     *
     */
    private static String fileToString(File file) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
