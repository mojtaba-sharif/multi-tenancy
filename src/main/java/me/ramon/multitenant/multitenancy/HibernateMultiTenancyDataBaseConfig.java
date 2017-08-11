package me.ramon.multitenant.multitenancy;


import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "me.ramon", name = "database-mode", havingValue = "hibernate-multitenant")
@Configuration
@AutoConfigureBefore({HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"me.ramon.multitenant.shared", "me.ramon.multitenant.multitenancy"})
public class HibernateMultiTenancyDataBaseConfig {

}
