package cn.javabook.chapter09.netty;

/**
 * 终端管理器，管理终端的各种状态
 *
 */
public class TerminalManager {
    private static final TerminalManager instance = new TerminalManager();

    public static TerminalManager getInstance() {
        return instance;
    }

    /**
     * 通过IMEI号判断是否已登录
     *
     * @param channelInfo
     * @return
     */
    public boolean isLogin(ChannelInfo channelInfo) {
        return channelInfo.getImei() != null;
    }

    /**
     * 登录方法
     *
     * @param imei
     * @param channelInfo
     * @return
     */
    public boolean login(String imei, ChannelInfo channelInfo) {
        if (exist(imei)) {
            channelInfo.setImei(imei);
            return true;
        }
        return false;
    }

    /**
     * 登出方法
     *
     * @param channelInfo
     */
    public void logout(ChannelInfo channelInfo) {
        channelInfo.setImei(null);
    }

    /**
     * 终端是否存在
     *
     * @param imei
     * @return
     */
    private boolean exist(String imei) {
        // 判断某个终端设备是否在缓存或数据库中存在
        return true;
    }
}
