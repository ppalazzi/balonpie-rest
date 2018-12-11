package com.palazzisoft.balonpie.service.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
@EnableTransactionManagement
//@EnableAutoConfiguration
public class DatabaseConfig {

	final static Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

	/*
	@Bean
	public DataSource dataSource() throws SQLException {
		LOG.info("Setting Database Connection" + url + "\n" + password);

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
		return ds;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws SQLException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.palazzisoft.balonpie.service.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("hibernate.jdbc.batch_size", "20");
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.hbm2ddl.auto", "create");
				setProperty("hibernate.current_session_context_class",
						"org.springframework.orm.hibernate5.SpringSessionContext");
			}
		};
	}

	*/
}
