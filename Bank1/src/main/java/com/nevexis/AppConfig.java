package com.nevexis;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
 @Autowired
   DataSourceProperties dataSourceProperties;

    @Bean
    @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
   public DataSource realDataSource() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
        DataSource dataSource = DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/test1")
                .username("root")
                .password("root")
                //.driverClassName(this.dataSourceProperties.getDriverClassName())
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
        return dataSource;
    }

   
}