package cn.javabook.chapter07.strategyfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 改进的策略模式
 * 
 */
public class StrategyFactory {
	private static StrategyFactory instance = null;

	private final Map<String, Finance> strategies = new HashMap<String, Finance>();

	private StrategyFactory() {
	}

	/**
	 * 懒汉单例模式
	 */
	public static StrategyFactory getInstance() {
		if (null == instance) {
			instance = new StrategyFactory();
			return instance;
		}
		return instance;
	}

	public void put(String name, Finance finance) {
		strategies.put(name, finance);
	}

	public Finance get(String name) {
		return strategies.get(name);
	}

	/**
	 * 初始化方法，在系统启动时执行一次。也可以放在数据库、缓存或配置文件中
	 */
	public void init() {
		strategies.put("t1", new T1Finance());
		strategies.put("t7", new T7Finance());
	}
}
