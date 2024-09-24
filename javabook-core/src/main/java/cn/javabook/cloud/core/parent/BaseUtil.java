package cn.javabook.cloud.core.parent;

import cn.javabook.cloud.core.BaseObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 所有工具类的抽象父类
 *
 */
public abstract class BaseUtil extends BaseObject {
    @JsonIgnore
    private static final long serialVersionUID = 8702633420270140030L;
}
