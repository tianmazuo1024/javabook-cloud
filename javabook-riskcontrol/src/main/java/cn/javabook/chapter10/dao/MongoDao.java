package cn.javabook.chapter10.dao;

import cn.javabook.chapter10.entity.LoginEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * MongoDao
 *
 */
@Component
public class MongoDao<T> {
    @Resource
    private MongoTemplate mongoTemplate;

    // 查找
    public List<T> find(final Query query, final Class<T> clazz, final String collectionName) {
        try {
            return mongoTemplate.find(query, clazz, collectionName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // 插入
    public boolean insert(final LoginEvent user, final String collectionName) {
        try {
            Object object = mongoTemplate.insert(user, collectionName);
            if (null != object) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    // 计数
    public long count(final Query query, final String collectionName) {
        try {
            return mongoTemplate.count(query, collectionName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }
}
