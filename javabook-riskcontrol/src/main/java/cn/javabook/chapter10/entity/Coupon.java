package cn.javabook.chapter10.entity;

/**
 * 无门槛优惠券
 *
 */
public class Coupon {
    // 用户编码
    private String userid;

    // 是否生日
    private boolean birthday;

    // 优惠券金额
    private int point;

    // 当月消费总金额
    private int buyMoney;

    // 当月消费次数
    private int buyNumbers;

    // 当月退货总金额
    private int refundMoney;

    // 当月退货次数
    private int refundNumbers;

    public Coupon() {
    }

    public Coupon(int point, int buyMoney) {
        this.point = point;
        this.buyMoney = buyMoney;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isBirthday() {
        return birthday;
    }

    public void setBirthday(boolean birthday) {
        this.birthday = birthday;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(int buyMoney) {
        this.buyMoney = buyMoney;
    }

    public int getBuyNumbers() {
        return buyNumbers;
    }

    public void setBuyNumbers(int buyNumbers) {
        this.buyNumbers = buyNumbers;
    }

    public int getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(int refundMoney) {
        this.refundMoney = refundMoney;
    }

    public int getRefundNumbers() {
        return refundNumbers;
    }

    public void setRefundNumbers(int refundNumbers) {
        this.refundNumbers = refundNumbers;
    }
}
