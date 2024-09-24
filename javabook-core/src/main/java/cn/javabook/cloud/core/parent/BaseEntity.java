package cn.javabook.cloud.core.parent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.javabook.cloud.core.BaseObject;

/**
 * 所有实体类的抽象父类
 *
 */
public abstract class BaseEntity extends BaseObject {
    @JsonIgnore
    private static final long serialVersionUID = -2670182907768387867L;
}
