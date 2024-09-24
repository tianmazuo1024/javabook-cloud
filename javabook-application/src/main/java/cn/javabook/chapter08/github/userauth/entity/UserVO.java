package cn.javabook.chapter08.github.userauth.entity;

import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserVO implements Serializable, RowMapper {
    private int id;
    private int userid;
    private int type;
    private String identifier;
    private String credential;
    private int expire;
    private String realname;
    private String nickname;
    private String avatar;
    private String cover;
    private Date createtime;
    private Date updatetime;

    @Override
    public Object mapRow(ResultSet result, int row) throws SQLException {
        UserVO user = new UserVO();
        user.setId(result.getInt("id"));
        user.setUserid(result.getInt("userid"));
        user.setType(result.getInt("type"));
        user.setIdentifier(result.getString("identifier"));
        user.setCredential(result.getString("credential"));
        user.setExpire(result.getInt("expire"));
        user.setRealname(result.getString("realname"));
        user.setNickname(result.getString("nickname"));
        user.setAvatar(result.getString("avatar"));
        user.setCover(result.getString("cover"));
        user.setCreatetime(result.getTimestamp("createtime"));
        user.setUpdatetime(result.getTimestamp("updatetime"));
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
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

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
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
