package cn.javabook.chapter10.entity;

import cn.javabook.chapter10.utils.DateUtils;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 黑名单
 * 
 */
public class BlackList implements RowMapper<BlackList> {
	private int id;

	private String username;

	// 类型：LOGIN_TIMES, ORDERS, ONLINE_DURATION, BUY_MONEY
	private String type;

	// 详情
	private String details;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public BlackList mapRow(ResultSet result, int row) throws SQLException {
		BlackList blackList = new BlackList();
		blackList.setId(result.getInt("id"));
		blackList.setUsername(result.getString("username"));
		blackList.setType(result.getString("type"));
		blackList.setDetails(result.getString("details"));
		blackList.setCreatetime(result.getTimestamp("createtime"));
		return blackList;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":%d, \"username\":\"%s\", \"type\":\"%s\", \"details\":\"%s\", \"createtime\":\"%s\"}",
				id, username, type, details, DateUtils.parse(createtime));
	}
}
