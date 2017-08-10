package io.api;

import java.sql.DriverManager;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfig {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean emf() {
		LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean();
		emFactoryBean.setDataSource(getDataSource());
		emFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emFactoryBean.setPackagesToScan("io.api.Entity");
		emFactoryBean.setJpaProperties(getProperties());
		
		return emFactoryBean;
	}
	
	@Bean
	public DataSource getDataSource() {
		
		DriverManagerDataSource dSource = new DriverManagerDataSource();
		dSource.setDriverClassName("com.mysql.jdbc.Driver");
		dSource.setUrl("jdbc:mysql://localhost:3306/weather-db");
		dSource.setUsername("root");
		dSource.setPassword("root");
		
		return dSource;
		
	}

	@Bean
	public PlatformTransactionManager txMgr(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		
		return properties;
	}
}
