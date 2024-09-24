package cn.javabook.chapter07.state.news;

/**
 * 订单状态上下文
 * 
 */
public class Context {
    private State state;
    private boolean ordered = false;

    /**
     * 初始状态就从下单开始
     */
    public Context() {
        this.state = new OrderedState(this);
    }

    public State getState() {
        return this.state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
