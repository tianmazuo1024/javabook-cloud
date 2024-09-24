package cn.javabook.chapter10.entity;

import cn.javabook.chapter10.utils.DateUtils;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用户
 * 
 */
public class User implements RowMapper<User> {
	private int id;

	private String username;

	private String password;

	// 启用或禁用：0:禁用，1:启用
	private int enabled;

	// 创建时间
	private Date createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public User mapRow(ResultSet result, int row) throws SQLException {
		User user = new User();
		user.setId(result.getInt("id"));
		user.setUsername(result.getString("username"));
		user.setPassword(result.getString("password"));
		user.setEnabled(result.getInt("enabled"));
		user.setCreatetime(result.getTimestamp("createtime"));
		return user;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":%d, \"username\":\"%s\", \"password\":\"%s\", \"enabled\":%d, \"createtime\":\"%s\"}",
				id, username, password, enabled, DateUtils.parse(createtime));
	}
}
