package me.ramon.multitenant;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

/**
 * Created by KADOOS on 01/28/2017.
 */

public class DefultProperties implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    static ConfigurableEnvironment configurableEnvironment;

    public static ConfigurableEnvironment getConfigurableEnvironment() {
        return configurableEnvironment;
    }

    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        configurableEnvironment=environment;
        Properties props = new Properties();
//        props.put("defualtDataBase.url", "jdbc:mysql://localhost:3306/address1");

        String databaseMode = environment.getProperty("de.yomoapp.database-mode");

//        String s2=environment.getProperty("spring.jpa.properties.hibernate.multiTenancy");
        if (databaseMode != null) {
            if (databaseMode.equals("hibernate-multitenant")) {
                props.put("spring.jpa.properties.hibernate.multiTenancy", "DATABASE");
                props.put("spring.jpa.properties.hibernate.tenant_identifier_resolver", "de.yomobit.multitenancy.BaseCurrentTenantIdentifierResolverImp");
                props.put("spring.jpa.properties.hibernate.multi_tenant_connection_provider", "de.yomobit.multitenancy.BaseMultiTenantConnectionProviderImp");
            }
            props.put("spring.jpa.hibernate.ddl-auto", "none");
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            props.put("hibernate.show_sql", "true");
            props.put(" logging.level.org.hibernate.SQL", "DEBUG");
            props.put("logging.level.org.hibernate.type.descriptor.sql.BasicBinder", "TRACE");
            props.put("spring.jpa.properties.hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
            environment.getPropertySources().addLast(new PropertiesPropertySource("application1", props));
        }
    }
}
