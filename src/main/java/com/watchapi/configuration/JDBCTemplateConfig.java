package com.watchapi.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages= {"org.watchapi.configuration"})
@Component
public class JDBCTemplateConfig {

	private final static String DATABSAE_URL = "jdbc:h2:tcp://localhost/~/watchapi";
	private final static String DATABSAE_DRIVER = "org.h2.Driver";
	private final static String DATABSAE_USERNAME = "sa";
	private final static String DATABSAE_PASSWORD = "";
	
	public static DataSource dataSource;
	
	public static JdbcTemplate jdbcTemplate;
	
//	public JDBCTemplateConfig() {
//		
//	}    
    
	@Bean
	public static JdbcTemplate getJDBCTemplate() {
		if(JDBCTemplateConfig.jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(getDataSource());
		}
		return jdbcTemplate;
	}
    
    private static DataSource getDataSource() {
        // Creates a new instance of DriverManagerDataSource and sets
        // the required parameters such as the Jdbc Driver class,
        // Jdbc URL, database user name and password.
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 	dataSource = new DataSource();
        dataSource.setDriverClassName(DATABSAE_DRIVER);
        dataSource.setUrl(DATABSAE_URL);
        dataSource.setUsername(DATABSAE_USERNAME);
        dataSource.setPassword(DATABSAE_PASSWORD);
        return dataSource;
    }	 
}
