package me.ramon.multitenant.shared;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "me.ramon")
public class DataBaseMode {

    String databaseMode;

    public String getDatabaseMode() {
        return databaseMode;
    }

    public void setDatabaseMode(String databaseMode) {
        this.databaseMode = databaseMode;
    }
}
