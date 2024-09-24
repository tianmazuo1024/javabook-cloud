package cn.javabook.chapter10.entity;

import java.util.List;

/**
 * 简单Flink-CEP对象 (仅用于单元测试)
 *
 */
public class SimpleCEPObject {
    private String name;

    private List<SimpleEvent> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SimpleEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SimpleEvent> events) {
        this.events = events;
    }

    public SimpleCEPObject(List<SimpleEvent> events, String name) {
        this.events = events;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("规则：").append(name).append(" ");
        int i = 0;
        for (SimpleEvent simpleEvent : events) {
            ++i;
            SimpleEvent event = simpleEvent;
            builder.append("事件").append(i).append(" ")
                   .append("uid:")
                   .append(event.getUserId())
                   .append(", 时间:")
                   .append(event.getEventTime())
                   .append(", 行为:")
                   .append(event.getEventName())
                   .append("\t");
        }
        return builder.toString();
    }
}
