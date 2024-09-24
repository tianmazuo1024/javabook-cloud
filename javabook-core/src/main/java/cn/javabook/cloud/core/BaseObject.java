package cn.javabook.cloud.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;

/**
 * 平台中所有类的公共父类
 *
 */
public class BaseObject implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = 6160454423036585408L;

    /**
     * 日志成员变量
     */
    @JsonIgnore
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 休眠
     */
    protected void sleepMillisSecond(int times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            logger.error("thread sleep has been interrupted");
        }
    }
}
