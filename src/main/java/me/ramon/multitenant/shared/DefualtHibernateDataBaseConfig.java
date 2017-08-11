package me.ramon.multitenant.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Romon on 1/14/2017.
 */
@Configuration

//@ComponentScan(basePackages = {"de.yomobit.shared","de.yomobit.shared"})
//@PropertySource("classpath:application.properties")
public class DefualtHibernateDataBaseConfig {   

    @Autowired
    private DataSource defualtDataSource;

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(defualtDataSource);
    }
}
