package cn.javabook.chapter08.oauth2.service;

import cn.javabook.chapter08.oauth2.entity.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户信息与认证服务
 *
 */
@Service
public class Auth2Service {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 用户注册
     *
     */
    public boolean register(String username, String password, String mobile, String email, String realname) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long id = -1;
        int temp = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO user_info(realname, nickname, avatar, cover) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, realname);
                ps.setString(2, "");
                ps.setString(3, "");
                ps.setString(4, "");
                return ps;
            }
        }, keyHolder);
        if (temp > 0) {
            id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }

        if (-1 != id) {
            String sql = "INSERT INTO user_auth(userid, type, identifier, credential, expire) VALUES (?, ?, ?, ?, ?)";
            List<Object[]> list = new ArrayList<>();
            Object[] objects = new Object[] { id, 0, username, password, -1 };
            list.add(objects);
            objects = new Object[] { id, 1, mobile, password, -1 };
            list.add(objects);
            objects = new Object[] { id, 2, email, password, -1 };
            list.add(objects);
            int[] result = jdbcTemplate.batchUpdate(sql, list);
            if (3 == result.length) {
                return true;
            }
        }
        return false;
    }

    /**
     * 保存用户第三方登录信息
     *
     */
    public boolean saveThirdLoginInfo(String identifier, String credential, String username, String avatar) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long id = -1;
        int temp = jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "INSERT INTO user_info(realname, nickname, avatar, cover) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, username);
                ps.setString(3, avatar);
                ps.setString(4, avatar);
                return ps;
            }
        }, keyHolder);
        if (temp > 0) {
            id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        }

        if (-1 != id) {
            String sql = "INSERT INTO user_auth(userid, type, identifier, credential, expire) VALUES (?, ?, ?, ?, ?)";
            List<Object[]> list = new ArrayList<>();
            Object[] objects = new Object[] { id, 100, identifier, credential, -1 };
            list.add(objects);
            int[] result = jdbcTemplate.batchUpdate(sql, list);
            if (3 == result.length) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用户登录
     *
     */
    public UserVO login(String identifier, String credential) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT a.id, a.userid, a.type, a.identifier, a.credential, ");
        builder.append("a.expire, i.realname, i.nickname, i.avatar, i.cover, ");
        builder.append("a.createtime, a.updatetime FROM user_info i, user_auth a ");
        builder.append("WHERE i.id = a.userid AND a.identifier = ? AND a.credential = ?");
        return (UserVO) jdbcTemplate.queryForObject(builder.toString(), new UserVO(), identifier, credential);
    }

    /**
     * 查询第三方登录用户
     *
     */
    public UserVO queryIdentifier(String identifier) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT a.id, a.userid, a.type, a.identifier, a.credential, ");
        builder.append("a.expire, i.realname, i.nickname, i.avatar, i.cover, ");
        builder.append("a.createtime, a.updatetime FROM user_info i, user_auth a ");
        builder.append("WHERE i.id = a.userid AND a.identifier = ?");
        return (UserVO) jdbcTemplate.queryForObject(builder.toString(), new UserVO(), identifier);
    }
}
