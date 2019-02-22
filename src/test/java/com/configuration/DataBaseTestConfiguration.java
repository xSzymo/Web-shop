package com.configuration;

import com.shop.configuration.ApplicationProperties;
import com.shop.configuration.database.DataBaseConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@WebAppConfiguration
@Ignore
public class DataBaseTestConfiguration {
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

	@BeforeClass
	public static void before() {
		ApplicationProperties.FALSE_WHILE_RUNNING_DB_TESTS = false;
	}

	@AfterClass
	public static void afterClass() {
		ApplicationProperties.FALSE_WHILE_RUNNING_DB_TESTS = true;
	}

	@Test
	public void testConnection() {
		try {
			entityManagerFactoryBean.getDataSource().getConnection();
		} catch (SQLException e) {
			fail("Check your data base connection");
		}
	}
}
