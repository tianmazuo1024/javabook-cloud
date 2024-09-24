package cn.javabook.chapter10.service;

import cn.javabook.chapter10.dao.MongoDao;
import cn.javabook.chapter10.entity.LoginEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 事件Service
 * 
 */
@Service
public class EventService {
	@Resource
	private MongoDao<LoginEvent> mongoDao;

	private final String LOGIN_COLLECTION = "login";

    // 一般事件
	public boolean insertEvent(LoginEvent event) {
		// 查询是否已有id相同的数据
		Query query = new Query();
		// 大于等于当前时间的前一分钟且小于等于当前时间
		query.addCriteria(Criteria.where("_id").is(event.getId()));
		List<LoginEvent> list = mongoDao.find(query, LoginEvent.class, "riskevent");
		if (null == list || list.isEmpty()) {
			return mongoDao.insert(event, LOGIN_COLLECTION);
		}
		return false;
	}

	// 可疑事件
	public boolean insertRiskEvent(LoginEvent event, String details) {
		// 查询是否已有id相同的数据
		Query query = new Query();
		// 大于等于当前时间的前一分钟且小于等于当前时间
		query.addCriteria(Criteria.where("_id").is(event.getId()));
		List<LoginEvent> list = mongoDao.find(query, LoginEvent.class, "riskevent");
		if (null == list || list.isEmpty()) {
			event.setDetails(details);
			return mongoDao.insert(event, "riskevent");
			// System.out.println("可疑事件：" + event.toString());
		}
		return false;
	}

	// 登录次数
	public long loginTimes(LoginEvent event) {
		if (event == null) {
			System.err.println("参数错误");
			return 0;
		}
		Query query = new Query();
		// 大于等于当前时间的前一分钟且小于等于当前时间
		query.addCriteria(Criteria.where("time").gte(event.getTime() - 60 * 1000L).lte(event.getTime()));
		return mongoDao.count(query, LOGIN_COLLECTION);
	}

	// 在网时长
	public int onlineDuration(LoginEvent event) {
		if (event == null) {
			System.err.println("参数错误");
			return -1;
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("logoutTime").lte(event.getTime() - 24 * 60 * 60 * 1000L));
		// 查到对象数量不为0则说明在网时长大于24小时
		return 1 <= mongoDao.count(query, LOGIN_COLLECTION) ? 1 : 0;
	}
}
