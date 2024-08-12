package com.ictedu.springapp.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config/database.properties")
public class DatabaseConfig {

    @Value("${driver-class-name}")
    private String driver;
    @Value("${oracle-url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;

    @Bean
    DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    DataSourceTransactionManager transactionManager(HikariDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate= new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }


}

