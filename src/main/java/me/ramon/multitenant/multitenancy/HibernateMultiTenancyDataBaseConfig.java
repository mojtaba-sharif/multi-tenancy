package me.ramon.multitenant.multitenancy;


import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "de.yomoapp", name = "database-mode", havingValue = "hibernate-multitenant")
@Configuration
@AutoConfigureBefore({HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"de.yomobit.multitenant.shared", "de.yomobit.multitenant.multitenancy"})
public class HibernateMultiTenancyDataBaseConfig {

}
