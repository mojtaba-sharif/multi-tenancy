package me.ramon.multitenant.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by KADOOS on 02/11/2017.
 */
@Component

@ConfigurationProperties(prefix="de.yomoapp")
public class YomoAppProp {
    String databaseMode;

    public String getDatabaseMode() {
        return databaseMode;
    }

    public void setDatabaseMode(String databaseMode) {
        this.databaseMode = databaseMode;
    }
}
