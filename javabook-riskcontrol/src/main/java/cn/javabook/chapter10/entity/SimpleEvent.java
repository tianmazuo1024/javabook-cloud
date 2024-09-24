package cn.javabook.chapter10.entity;

/**
 * 简单的行为事件对象 (仅用于单元测试)
 *
 */
public class SimpleEvent {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 事件发生时间
     */
    private Long eventTime;

    /**
     * 事件名称
     */
    private String eventName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public SimpleEvent(Integer userId, String eventName, Long eventTime) {
        this.userId = userId;
        this.eventName = eventName;
        this.eventTime = eventTime;
    }
}
