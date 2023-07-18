package com.lsgggg123.h2.dao;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@Import({DataSourceAutoConfiguration.class})
@TestPropertySource({"classpath:application-test.properties"})
public abstract class AbstractTestBase extends AbstractTransactionalTestNGSpringContextTests {
}

