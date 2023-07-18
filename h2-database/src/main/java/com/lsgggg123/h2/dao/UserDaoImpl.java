package com.lsgggg123.h2.dao;

import com.lsgggg123.h2.mode.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from user_table where id = ?", (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setNickName(rs.getString("nick_name"));
                user.setGmtCreate(rs.getDate("gmt_create"));
                user.setGmtModified(rs.getDate("gmt_modified"));
                return user;
            }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public long add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("add user required not null");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into user_table (`name`, `age`, `nick_name`) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getNickName());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            return (Integer) keys.get("ID");
        }

        throw new IllegalStateException("Can not generated primary key.");
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update user_table set age = ?, name = ?, nick_name = ? where id = ?", user.getAge(), user.getName(),
                user.getNickName(), user.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from user_table where id = ?", id);
    }
}
