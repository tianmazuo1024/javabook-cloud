package cn.javabook.chapter10.entity;

import cn.javabook.chapter10.utils.DateUtils;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用户订单
 * 
 */
public class Order implements RowMapper<Order> {
	private int id;

	// 用户名
	private String username;

	// 详情
	private String details;

	// 订单状态
	private int status;

	// 订单金额
	private int money;

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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public Order mapRow(ResultSet result, int row) throws SQLException {
		Order order = new Order();
		order.setId(result.getInt("id"));
		order.setUsername(result.getString("username"));
		order.setDetails(result.getString("details"));
		order.setStatus(result.getInt("status"));
		order.setMoney(result.getInt("money"));
		order.setCreatetime(result.getTimestamp("createtime"));
		return order;
	}

	@Override
	public String toString() {
		return String.format("{\"id\":%d, \"username\":\"%s\", \"details\":\"%s\", \"status\":%d, \"money\":%d, \"createtime\":\"%s\"}",
				id, username, details, status, money, DateUtils.parse(createtime));
	}
}
