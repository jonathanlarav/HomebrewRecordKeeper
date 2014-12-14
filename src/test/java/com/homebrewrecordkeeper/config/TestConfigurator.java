package com.homebrewrecordkeeper.config;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by Jonathan on 30/11/2014.
 */
@ComponentScan(basePackages = {"com.homebrewrecordkeeper.dao","com.homebrewrecordkeeper.service"})
@PropertySource({"classpath:hibernate.properties"})
@EnableTransactionManagement
@Configuration
public class TestConfigurator {

    @Resource
    private Environment env;

    @Bean(name = "dataSource")
    public DriverManagerDataSource getDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getRequiredProperty("hibernate.driver"));
        driverManagerDataSource.setUrl(env.getRequiredProperty("hibernate.url"));
        driverManagerDataSource.setUsername(env.getRequiredProperty("hibernate.username"));
        driverManagerDataSource.setPassword(env.getRequiredProperty("hibernate.password"));
        return driverManagerDataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setPackagesToScan("com.homebrewrecordkeeper.entity");
        return sessionFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.showSql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl"));
        return properties;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        return hibernateTransactionManager;
    }

    /**
     * Created by Jonathan on 13/12/2014.
     */

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = TestConfigurator.class)
    @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
            DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class })
    @DatabaseSetup("classpath:sampleData.xml")
    public abstract static class DbUnitBaseIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    }
}
