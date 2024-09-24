package cn.javabook.chapter08.oauth2.entity;

import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserAuth implements Serializable, RowMapper {
    private int id;
    private int userid;
    private int type;
    private String identifier;
    private String credential;
    private int expire;
    private Date createtime;
    private Date updatetime;

    @Override
    public Object mapRow(ResultSet result, int row) throws SQLException {
        UserAuth userAuth = new UserAuth();
        userAuth.setId(result.getInt("id"));
        userAuth.setUserid(result.getInt("userid"));
        userAuth.setType(result.getInt("type"));
        userAuth.setIdentifier(result.getString("identifier"));
        userAuth.setCredential(result.getString("credential"));
        userAuth.setExpire(result.getInt("expire"));
        userAuth.setCreatetime(result.getTimestamp("createtime"));
        userAuth.setUpdatetime(result.getTimestamp("updatetime"));
        return userAuth;
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
