package com.shop.configuration.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.shop.configuration.ApplicationProperties;
import com.shop.configuration.system.ApplicationPropertiesInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories("com")
@ComponentScan("com")
@Configuration
public class DataBaseConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(getJpaProperties());
        return factoryBean;
    }

    @Bean
    public Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        if (ApplicationProperties.USE_APPLICATION_PROPERTIES_DATA)
            new ApplicationPropertiesInitializer();
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(ApplicationProperties.SERVER_NAME);
        dataSource.setPort(Integer.parseInt(ApplicationProperties.PORT));
        dataSource.setDatabaseName(ApplicationProperties.DATABASE_NAME);
        dataSource.setUser(ApplicationProperties.DATABASE_USER_NANE);
        dataSource.setPassword(ApplicationProperties.DATABASE_USER_PASSWORD);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
