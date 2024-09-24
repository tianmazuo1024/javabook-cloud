package cn.javabook.cloud.core.parent;

/**
 * 所有接口的公共父接口
 *
 */
public interface IService {
    // redis：查询对象状态
    public Object getSavedObject(String key);

    // redis：保存对象状态
    public void setSavedObject(String key, String value, long expire);
}
