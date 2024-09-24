package cn.javabook.cloud.core.parent;

import cn.javabook.cloud.core.BaseObject;
import cn.javabook.cloud.core.utils.IdGeneratorUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 所有服务类的抽象父类
 *
 */
public abstract class BaseService extends BaseObject {
    @JsonIgnore
    private static final long serialVersionUID = -7018152962450080179L;

    // 生成全局唯一编码
    public Long nextId(Long datacenterId, Long machineId) {
        return IdGeneratorUtil.getInstance(datacenterId, machineId).nextId();
    }
}
