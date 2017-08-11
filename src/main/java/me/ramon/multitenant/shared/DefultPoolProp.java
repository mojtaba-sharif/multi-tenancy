package me.ramon.multitenant.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by KADOOS on 01/30/2017.
 */
@Component

@ConfigurationProperties(prefix = "defualtPool")
public class DefultPoolProp {

    String maxPoolSize;
    String minPoolSize;
    String initialPoolSize;
    String poolMaxConnectionAge;

    public String getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(String initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public String getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(String maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public String getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(String minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public String getPoolMaxConnectionAge() {
        return poolMaxConnectionAge;
    }

    public void setPoolMaxConnectionAge(String poolMaxConnectionAge) {
        this.poolMaxConnectionAge = poolMaxConnectionAge;
    }
}
