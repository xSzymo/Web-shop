package com;

import com.shop.configuration.ApplicationConfig;
import com.shop.configuration.DataBaseConfiguration;
import com.shop.data.services.AddressService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@WebAppConfiguration
public abstract class DataBaseTestConfiguration {
    @Autowired
    AddressService addressService;

    @BeforeClass
    public static void before() {
        ApplicationConfig.FALSE_WHILE_RUNNING_DB_TESTS = false;
    }

    @AfterClass
    public static void afterClass() {
        ApplicationConfig.FALSE_WHILE_RUNNING_DB_TESTS = true;
    }

}
