package cn.javabook.chapter10.service;

import cn.javabook.chapter10.dao.MySQLDao;
import cn.javabook.chapter10.entity.Order;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 订单Service
 *
 */
@Service
public class OrderService {
	@Resource
	private MySQLDao<Order> mySQLDao;

	/**
	 * 凌晨下单数量
	 *
	 */
	public int getOrderNumber(final String username) {
		// 这里偷懒，就直接把日期写死了，实际开发时肯定不能这么写:）
        String sb = "SELECT id, username, details, status, money, createtime FROM rc_order WHERE username = ? AND createtime >= '2024-01-01 03:00:00' AND createtime <= '2024-01-01 05:00:00'";
		List<Order> list = (List<Order>) mySQLDao.find(sb.toString(), new Order(), username);
		return null != list ? list.size() : 0;
	}

	/**
	 * 当月消费金额
	 *
	 */
	public int getBuyMoneyCurrentMonth(final String username) {
		// 这里也偷懒，就直接把日期写死了，实际开发时肯定不能这么写:）
		String sb = "SELECT SUM(money) FROM rc_order WHERE username = '" + username + "' AND createtime >= '2024-01-01 00:00:00' AND createtime <= '2024-01-31 23:59:59'";
		return mySQLDao.sum(sb);
	}
}
