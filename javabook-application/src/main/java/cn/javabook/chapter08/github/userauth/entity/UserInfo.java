package cn.javabook.chapter08.github.userauth.entity;

import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserInfo implements Serializable, RowMapper {
    private int id;
    private String realname;
    private String nickname;
    private String avatar;
    private String cover;
    private Date createtime;
    private Date updatetime;

    @Override
    public Object mapRow(ResultSet result, int row) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(result.getInt("id"));
        userInfo.setRealname(result.getString("realname"));
        userInfo.setNickname(result.getString("nickname"));
        userInfo.setAvatar(result.getString("avatar"));
        userInfo.setCover(result.getString("cover"));
        userInfo.setCreatetime(result.getTimestamp("createtime"));
        userInfo.setUpdatetime(result.getTimestamp("updatetime"));
        return userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
