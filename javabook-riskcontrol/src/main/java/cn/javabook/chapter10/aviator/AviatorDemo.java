package cn.javabook.chapter10.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.util.HashMap;
import java.util.Map;

/**
 * Aviator Demo
 *
 */
public class AviatorDemo {
    public static void main(String[] args) {
        // 算术运算
        String exp1 = "1 + 2 + 3";
        Long result = (Long) AviatorEvaluator.execute(exp1);
        System.out.println("1. " + result);

        String exp2 = "1.1 + 2.2 + 3.3";
        Double result2 = (Double) AviatorEvaluator.execute(exp2);
        System.out.println("2. " + result2);

        // 逻辑运算
        String exp3 = "(1 > 0 || 0 < 1) && 1 != 0";
        System.out.println("3. " + AviatorEvaluator.execute(exp3));

        // 三元运算
        String exp4 = "2 > 1 ? \"2 > 1\" : none";
        System.out.println("4. " + AviatorEvaluator.execute(exp4));

        // 变量传入
        String exp5 = "a > b";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        System.out.println("5. " + AviatorEvaluator.execute(exp5, map));

        // 自定义函数的调用（固定参数）
        AviatorEvaluator.addFunction(new AviatorCustomFunction());
        String exp6 = "add(a, b)";
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("a", 1d);
        map2.put("b", 2d);
        Expression expression6 = AviatorEvaluator.compile(exp6, true);
        System.out.println("6. " + expression6.execute(map2));

        // 自定义函数的调用 (可变参数)
        AviatorEvaluator.addFunction(new AviatorCustomFunctionArgs());
        String exp7 = "customArgs(a, b, c)";
        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("a", 1.1d);
        map7.put("b", 2.2d);
        map7.put("c", 3.3d);
        Expression expression7 = AviatorEvaluator.compile(exp7, true);
        System.out.println("7. " + expression7.execute(map7));
    }
}
