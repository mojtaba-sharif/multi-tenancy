package me.ramon.multitenant.singletenancy;


import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "de.yomoapp", name = "database-mode", havingValue = "hibernate-singletenant")
@Configuration
@AutoConfigureBefore({HibernateJpaAutoConfiguration.class})
//@PropertySource("application.properties")
//@EntityScan("de.yomobit.addressmanagment.domain")
//@Import({defualtDataBaseConfig.class, DefualtHibernateDataBaseConfig.class})
@ComponentScan(basePackages = {"de.yomobit.multitenant.shared", "de.yomobit.multitenant.singletenancy"})
public class HibernateSingleTenancyDataBaseConfig {

}
