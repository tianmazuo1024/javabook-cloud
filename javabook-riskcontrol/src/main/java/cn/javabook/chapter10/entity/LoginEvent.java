package cn.javabook.chapter10.entity;

import cn.javabook.chapter10.utils.DateUtils;
import org.springframework.data.annotation.Id;
import java.util.Date;

/**
 * 登录事件
 *
 */
public class LoginEvent {
    @Id
    private String id;

    // 用户名
    private String username;

    // 详情
    private String details;

    // 用户等级
    private int level;

    // 订单量
    private int orders;

    // 当月消费金额
    private int buyMoney;

    // 上次下线时间
    private long logoutTime;

    // 登录时间
    private long time;

    public LoginEvent() {
    }

    public LoginEvent(int level) {
        this.level = level;
    }

    public LoginEvent(String id, String username, String details, int level, int orders, int buyMoney, long logoutTime, long time) {
        this.id = id;
        this.username = username;
        this.details = details;
        this.level = level;
        this.orders = orders;
        this.buyMoney = buyMoney;
        this.logoutTime = logoutTime;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(int buyMoney) {
        this.buyMoney = buyMoney;
    }

    public long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(long logoutTime) {
        this.logoutTime = logoutTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"username\":\"%s\", \"details\":\"%s\", \"level\":%d, \"orders\":%d, \"buyMoney\":%d, \"logoutTime\":\"%s\", \"time\":\"%s\"}",
                id, username, details, level, orders, buyMoney, DateUtils.parse(new Date(logoutTime)), DateUtils.parse(new Date(time)));
    }
}
