package cn.javabook.cloud.core.parent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.javabook.cloud.core.BaseObject;

/**
 * 所有数据访问类的抽象父类
 *
 */
public abstract class BaseDao extends BaseObject {
    @JsonIgnore
    private static final long serialVersionUID = 5941559956832656235L;
}
