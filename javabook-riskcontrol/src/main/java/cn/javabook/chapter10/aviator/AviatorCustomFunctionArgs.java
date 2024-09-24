package cn.javabook.chapter10.aviator;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.Map;

/**
 * Aviator自定义函数（可变参数）
 *
 */
public class AviatorCustomFunctionArgs extends AbstractVariadicFunction {
    @Override
    public AviatorObject variadicCall(Map<String, Object> map, AviatorObject... args) {
        double sum = 0d;
        for (AviatorObject arg : args) {
            Number a = FunctionUtils.getNumberValue(arg, map);
            sum += a.doubleValue();
        }
        return new AviatorDouble(sum);
    }

    @Override
    public String getName() {
        return "customArgs";
    }
}
