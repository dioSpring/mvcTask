package org.diosoft.spring.mvcTask.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.context.request.async.TimeoutDeferredResultProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan("org.diosoft.spring.mvcTask")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        super.configureAsyncSupport(configurer);
        configurer.setDefaultTimeout(2500);
        configurer.setTaskExecutor(threadPoolTaskExecutor());
        configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
        configurer.registerDeferredResultInterceptors(timeoutDeferredResultProcessingInterceptor());
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor= new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(25);
        return threadPoolTaskExecutor;
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor(){
        return new TimeoutCallableProcessingInterceptor();
    }

    @Bean
    public TimeoutDeferredResultProcessingInterceptor timeoutDeferredResultProcessingInterceptor(){
        return new TimeoutDeferredResultProcessingInterceptor();
    }
    
    public DataSource getDataSource(){
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/test");
    	dataSource.setUsername("root");
    	dataSource.setPassword("Alkogol.123");
    	return dataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
    	
    	Properties hibernateProperties = new Properties();
    	hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	hibernateProperties.setProperty("hibernate.show_sql", "true");
    	hibernateProperties.setProperty("hibernate.current_session_context_class", "thread");
    	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    	
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    	sessionFactory.setDataSource(getDataSource());
    	sessionFactory.setPackagesToScan("sessionFactory");
    	sessionFactory.setHibernateProperties(hibernateProperties);
    	return sessionFactory;
    }
    
    
    /*
     * - create dataSource as method + 
     * - create session as bean +
     * - create DAO and inject session +
     * - create mapping in model user (name,databithday) +
     * - implement in DAO user methods (save,get)  +
     * - insert DAO in controller + 
     * - implement all of this as xml
     */
}
