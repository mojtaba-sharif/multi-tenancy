package me.ramon.multitenant.shared;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Configuration
public class DefualtDataBaseConfig {

    @Autowired
    DefultDatabaseProp properties;
    @Autowired
    DefultPoolProp defultPoolProp;

    @Bean
    public DataSource defualtDataSource() {
        ComboPooledDataSource defualtDatasource = new ComboPooledDataSource();
        defualtDatasource.setJdbcUrl(properties.getUrl());
        defualtDatasource.setUser(properties.getUsername());
        defualtDatasource.setPassword(properties.getPassword());
        try {
            defualtDatasource.setDriverClass(properties.getDriver());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        defualtDatasource.setInitialPoolSize(Integer.valueOf(defultPoolProp.getInitialPoolSize()));
        defualtDatasource.setMaxPoolSize(Integer.parseInt(defultPoolProp.getMaxPoolSize()));
        defualtDatasource.setMinPoolSize(Integer.parseInt(defultPoolProp.getMinPoolSize()));
        defualtDatasource.setMaxConnectionAge(Integer.parseInt(defultPoolProp.getPoolMaxConnectionAge()));
        return defualtDatasource;
    }

}
