package cn.javabook.chapter10.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import java.util.Map;

/**
 * Aviator自定义函数（不可变参数）
 *
 */
public class AviatorCustomFunction extends AbstractFunction {
    /**
     * 实现函数逻辑
     *
     */
    @Override
    public AviatorObject call(Map<String, Object> map, AviatorObject arg1, AviatorObject arg2) {
        Number num1 = FunctionUtils.getNumberValue(arg1, map);
        Number num2 = FunctionUtils.getNumberValue(arg2, map);
        return new AviatorDouble(num1.doubleValue() + num2.doubleValue());
    }

    /**
     * 定义函数名
     *
     */
    @Override
    public String getName() {
        return "add";
    }
}

