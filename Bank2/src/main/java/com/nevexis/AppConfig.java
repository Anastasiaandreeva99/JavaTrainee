package com.nevexis;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Bean
// @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
	public DataSource realDataSource() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		DataSource dataSource = DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/test1").username("root")
				.password("root")
				// .driverClassName(this.dataSourceProperties.getDriverClassName())
				.driverClassName("com.mysql.cj.jdbc.Driver").build();
		System.out.println(dataSource);
		return dataSource;
	}

}
