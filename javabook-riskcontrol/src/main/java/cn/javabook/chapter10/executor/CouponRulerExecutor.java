package cn.javabook.chapter10.executor;

import cn.javabook.chapter10.entity.Coupon;

/**
 * 规则执行器
 *
 */
public class CouponRulerExecutor {
    /**
     * 生日规则
     *
     */
    private void birthdayRule(Coupon coupon) {
        if (coupon.isBirthday()) {
            coupon.setPoint(coupon.getPoint() + 5);
        }
    }

    /**
     * 当月购物总金额规则
     *
     */
    private void buyMoneyRule(Coupon coupon, int buyMoney) {
        int amount = coupon.getPoint();
        if (buyMoney <= 100) {
            coupon.setPoint(0);
        } else if (buyMoney > 100 && buyMoney <= 500) {
            coupon.setPoint(amount + 10);
        } else if (buyMoney > 500 && buyMoney <= 1000) {
            coupon.setPoint(amount + 50);
        } else if (buyMoney > 1000) {
            coupon.setPoint(amount + 100);
        }
    }

    /**
     * 当月消费次数规则
     *
     */
    private void buyNumbersRule(Coupon coupon, int buyNumbers) {
        int amount = coupon.getPoint();
        if (buyNumbers == 1) {
            coupon.setPoint(amount + 2);
        } else if (buyNumbers > 1 && buyNumbers <= 3) {
            coupon.setPoint(amount + 10);
        } else if (buyNumbers > 3 && buyNumbers <= 5) {
            coupon.setPoint(amount + 50);
        } else if (buyNumbers > 5) {
            coupon.setPoint(amount + 100);
        }
    }

    /**
     * 当月退款总金额规则
     *
     */
    private void refundMoneyRule(Coupon coupon, int refundMoney) {
        int amount = coupon.getPoint();
        if (refundMoney < 100) {
            if (amount > 10) {
                coupon.setPoint(amount - 10);
            }
        } else if (refundMoney > 100 && refundMoney <= 500) {
            if (amount >= 50) {
                coupon.setPoint(amount - 50);
            } else {
                coupon.setPoint((int) (amount * 0.5));
            }
        } else if (refundMoney > 500 && refundMoney <= 1000) {
            if (amount >= 200) {
                coupon.setPoint(amount - 200);
            } else {
                coupon.setPoint(amount - (int) (amount * 0.6));
            }
        } else if (refundMoney > 1000) {
            if (amount >= 300) {
                coupon.setPoint(amount - 300);
            } else {
                coupon.setPoint(amount - (int) (amount * 0.8));
            }
        }
    }

    /**
     * 当月退款次数规则
     *
     */
    private void refundNumbersRule(Coupon coupon, int refundNumbers) {
        int amount = coupon.getPoint();
        if (refundNumbers == 1) {
            if (amount > 10) {
                coupon.setPoint(amount - 10);
            }
        } else if (refundNumbers > 1 && refundNumbers <= 3) {
            if (amount >= 100) {
                coupon.setPoint(amount - 100);
            } else {
                coupon.setPoint((int) (amount * 0.5));
            }
        } else if (refundNumbers > 3 && refundNumbers <= 7) {
            if (amount >= 200) {
                coupon.setPoint(amount - 200);
            } else {
                coupon.setPoint(amount - (int) (amount * 0.8));
            }
        } else if (refundNumbers > 7) {
            coupon.setPoint(0);
        }
    }

    public static void main(String[] args) {
    }
}
