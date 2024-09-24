package cn.javabook.chapter10.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * MySQLDao
 * 
 */
@Component
public class MySQLDao<T> {
	@Resource
	private JdbcTemplate jdbcTemplate;

	// 查询数量
	public int sum(final String sql, final @Nullable Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (DataAccessException e) {
			System.err.println(e.getMessage());
		}
		return -1;
	}

	// 获得列表
	public List<?> find(final String sql, final RowMapper<?> rowMapper, final @Nullable Object... args) {
		try {
			List<?> list = jdbcTemplate.query(sql, rowMapper, args);
			if (!list.isEmpty()) {
				return list;
			}
		} catch (DataAccessException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	// 获得列表
	public Object findOne(final String sql, final RowMapper<?> rowMapper, final @Nullable Object... args) {
		try {
			Object object = jdbcTemplate.queryForObject(sql, rowMapper, args);
			if (null != object) {
				return object;
			}
		} catch (DataAccessException ignored) {
		}
		return null;
	}

	// 更新或删除数据
	public boolean update(final String sql, final @Nullable Object... args) throws Exception {
		try {
			return 0 <= jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("update or remove object exception");
		}
	}

	// 创建数据
	public int create(final String sql, final @Nullable Object... args) {
		try {
			if (0 < jdbcTemplate.update(sql, args)) {
				return 0;
			}
			return -1;
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("data duplicate exception");
		} catch (DataAccessException e) {
			throw new RuntimeException("create data exception");
		}
	}
}
