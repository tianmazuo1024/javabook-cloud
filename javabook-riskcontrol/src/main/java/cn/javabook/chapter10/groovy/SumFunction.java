package cn.javabook.chapter10.groovy;

import com.googlecode.aviator.runtime.function.AbstractVariadicFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.Map;

/**
 * Aviator自定义函数 (可变参数)
 *
 */
public class SumFunction extends AbstractVariadicFunction {
    private String fieldName;

    public SumFunction(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> map, AviatorObject... args) {
        double sum = 0D;
        for (AviatorObject arg : args) {
            Number number = FunctionUtils.getNumberValue(arg, map);
            Double value = Double.valueOf((String) map.get(fieldName));
            sum += number.doubleValue();
        }
        return new AviatorDouble(sum);
    }

    @Override
    public String getName() {
        return "sum";
    }
}
