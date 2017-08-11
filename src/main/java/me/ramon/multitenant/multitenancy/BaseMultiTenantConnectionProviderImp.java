package me.ramon.multitenant.multitenancy;



import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import me.ramon.multitenant.DefultProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Component
public class BaseMultiTenantConnectionProviderImp extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    public static final String DEFAULT_TENANT_ID = "NON_DEFUALT_TENANT_ID";
    protected RestTemplate restTemplate = new RestTemplate();

    Log logger = LogFactory.getLog(getClass());

    private String key;
    private String driver;
    private String url;
    private String username;
    private String password;
    private int initialPoolSize;
    private int maxPoolSize;
    private int minPoolSize;
    private int poolMaxConnectionAge;


    protected DriverManagerDataSource defaultDataSource;


    @Override
    public DataSource selectAnyDataSource() {
        setProperties();
        defaultDataSource = new DriverManagerDataSource();
        defaultDataSource.setUrl(url);
        defaultDataSource.setUsername(username);
        defaultDataSource.setPassword(password);
        try {
            defaultDataSource.setDriverClassName(driver);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return defaultDataSource;
    }

    private void setProperties() {
        key = DefultProperties.getConfigurableEnvironment().getProperty("defualtDataBase.key");
        url = DefultProperties.getConfigurableEnvironment().getProperty("defualtDataBase.url");
        driver = DefultProperties.getConfigurableEnvironment().getProperty("defualtDataBase.driver");
        username = DefultProperties.getConfigurableEnvironment().getProperty("defualtDataBase.username");
        password = DefultProperties.getConfigurableEnvironment().getProperty("defualtDataBase.password");
        initialPoolSize = Integer.valueOf(DefultProperties.getConfigurableEnvironment().getProperty("defualtPool.initialPoolSize"));
        maxPoolSize = Integer.valueOf(DefultProperties.getConfigurableEnvironment().getProperty("defualtPool.maxPoolSize"));
        minPoolSize = Integer.valueOf(DefultProperties.getConfigurableEnvironment().getProperty("defualtPool.minPoolSize"));
        poolMaxConnectionAge = Integer.valueOf(DefultProperties.getConfigurableEnvironment().getProperty("defualtPool.poolMaxConnectionAge"));
    }

    private PooledDataSource proccessDataSource(String tenantIdentifier) {
        System.out.println("connection is null, start process " + tenantIdentifier);
        DataSourceWrapper sourceWrapper = DataSourceRegistery.getInstance().get(tenantIdentifier);
        if (sourceWrapper == null) {
            System.out.println("datasource is null for tenantid in map " + tenantIdentifier);
            sourceWrapper = restTemplate.getForObject("http://localhost:8888/database-management/tenant/{tenantId}", DataSourceWrapper.class, tenantIdentifier);
            System.out.println("get datasurce in database management " + tenantIdentifier);
            sourceWrapper.setTenantId(tenantIdentifier);
            DataSourceRegistery.getInstance().put(sourceWrapper.getTenantId(), sourceWrapper);
            System.out.println("put datasource in map " + tenantIdentifier);
        }
        System.out.println("create connection for tenantId " + tenantIdentifier);
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource(sourceWrapper.getTenantId());
        comboPooledDataSource.setJdbcUrl(sourceWrapper.getUrl());
        comboPooledDataSource.setUser(sourceWrapper.getUsername());
        comboPooledDataSource.setPassword(sourceWrapper.getPassword());
        comboPooledDataSource.setInitialPoolSize(sourceWrapper.getInitialPoolSize());
        comboPooledDataSource.setMinPoolSize(sourceWrapper.getMinPoolSize());
        comboPooledDataSource.setMaxPoolSize(sourceWrapper.getMaxPoolSize());
        comboPooledDataSource.setMaxConnectionAge(sourceWrapper.getPoolMaxConnectionAge());
        try {
            comboPooledDataSource.setDriverClass(sourceWrapper.getDriver());
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("finish process for tenantId " + tenantIdentifier);
        return comboPooledDataSource;
    }

    @Override
    public synchronized DataSource selectDataSource(String tenantIdentifier) {
        PooledDataSource pooledDataSource = C3P0Registry.pooledDataSourceByName(tenantIdentifier);
        if (pooledDataSource == null) {
            pooledDataSource = proccessDataSource(tenantIdentifier);
        }
        return pooledDataSource;
    }

}