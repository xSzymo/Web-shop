package com.configuration;

import com.shop.configuration.AppConfig;
import com.shop.configuration.AppInitializer;
import com.shop.configuration.ApplicationConfig;
import com.shop.configuration.DataBaseConfiguration;
import com.shop.configuration.SecurityConfig;
import com.shop.configuration.SecurityWebAppInitializer;
import com.shop.data.services.AddressService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@WebAppConfiguration
public class DataBaseTestConfiguration {
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @BeforeClass
    public static void before() {
        ApplicationConfig.FALSE_WHILE_RUNNING_DB_TESTS = false;
    }

    @AfterClass
    public static void afterClass() {
        ApplicationConfig.FALSE_WHILE_RUNNING_DB_TESTS = true;
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
