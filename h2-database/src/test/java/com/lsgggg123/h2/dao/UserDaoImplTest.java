package com.lsgggg123.h2.dao;

import com.lsgggg123.h2.dao.UserDaoImplTest.TestConfig;
import com.lsgggg123.h2.mode.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDataSourceConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.TransactionManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sql.DataSource;

@ContextConfiguration(classes = {TestConfig.class, SqlInitializationAutoConfiguration.class})
public class UserDaoImplTest extends AbstractTestBase implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private UserDao userDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
        userDao = new UserDaoImpl(jdbcTemplate);
    }

    @Test
    public void testGetById() {
        User user = userDao.getById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId().longValue(), 1L);
        Assert.assertEquals(user.getName(), "Tom");
        Assert.assertEquals(user.getAge().intValue(), 1);
        Assert.assertEquals(user.getNickName(), "tom cat");
    }

    @Test
    public void testAdd() {
        String name = "newName";
        int age = 80;
        String nickName = "newNickName";

        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setNickName(nickName);

        long id = userDao.add(newUser);
        User user = userDao.getById(id);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId().longValue(), id);
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getAge().intValue(), age);
        Assert.assertEquals(user.getNickName(), nickName);
    }

    @Test
    public void testUpdate() {
        Long id = 3L;
        String name = "updateName";
        int age = 60;
        String nickName = "updateNickName";

        User updateUser = new User();
        updateUser.setId(id);
        updateUser.setName(name);
        updateUser.setAge(age);
        updateUser.setNickName(nickName);

        int update = userDao.update(updateUser);
        Assert.assertEquals(update, 1);

        User user = userDao.getById(id);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), id);
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getAge().intValue(), age);
        Assert.assertEquals(user.getNickName(), nickName);
    }

    @Test
    public void testDeleteById() {
        int delete = userDao.deleteById(4L);
        Assert.assertEquals(delete, 1);

        User user = userDao.getById(4L);
        Assert.assertNull(user);
    }


    @Configuration(proxyBeanMethods = false)
    @Import(EmbeddedDataSourceConfiguration.class)
    static class TestConfig {

        @Bean
        public TransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }
}