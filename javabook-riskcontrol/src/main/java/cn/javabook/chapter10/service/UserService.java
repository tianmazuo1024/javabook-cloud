package cn.javabook.chapter10.service;

import cn.javabook.chapter10.dao.MySQLDao;
import cn.javabook.chapter10.entity.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户Service
 *
 */
@Service
public class UserService {
	@Resource
	private MySQLDao<User> mySQLDao;

	public boolean lock(final String username) throws Exception {
        String sb = "UPDATE rc_user SET enabled = 0 WHERE username = '" + username + "'";
		return mySQLDao.update(sb);
	}
}
