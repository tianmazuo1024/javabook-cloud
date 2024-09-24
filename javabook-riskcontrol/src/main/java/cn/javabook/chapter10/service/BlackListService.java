package cn.javabook.chapter10.service;

import cn.javabook.chapter10.dao.MySQLDao;
import cn.javabook.chapter10.entity.BlackList;
import cn.javabook.chapter10.utils.DateUtils;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 清单Service
 *
 */
@Service
public class BlackListService {
	@Resource
	private MySQLDao<BlackList> mySQLDao;

	private final Map<String, BlackList> map = new ConcurrentHashMap<>();

	@PostConstruct
	public void cache() {
		List<BlackList> blackLists = queryAll();
		if (null != blackLists) {
			for (BlackList blackList : blackLists) {
				map.put(blackList.getUsername(), blackList);
			}
			System.out.println("-----------------------------------");
			System.out.println("黑名单更新成功");
			System.out.println("-----------------------------------");
		}
	}

	public boolean contain(final String username) {
		BlackList blackList = map.get(username);
		return blackList != null;
	}

	public boolean save(final String username, final String details) {
		String sql = "INSERT INTO rc_blacklist (username, type, details, createtime) VALUES (?, ?, ?, ?)";
		String type = "LOGIN_TIMES";
		BlackList blackList = query(username, type, details);
		int code = 0;
		if (null == blackList) {
			code = mySQLDao.create(sql, username, type, details, DateUtils.parse(new Date()));
		}
		// 同时更新缓存
		map.clear();
		cache();
		return code == 0;
    }

	public BlackList query(final String username, final String type, final String details) {
		String sql = "SELECT id, username, type, details, createtime FROM rc_blacklist WHERE username = ? AND type = ? AND details = ?";
		return  (BlackList) mySQLDao.findOne(sql, new BlackList(), username, type, details);
	}

	public List<BlackList> queryAll() {
		String sql = "SELECT id, username, type, details, createtime FROM rc_blacklist";
		List<?> list = mySQLDao.find(sql, new BlackList());
		if (null == list) {
			return null;
		}
		return (List<BlackList>) list;
	}
}
