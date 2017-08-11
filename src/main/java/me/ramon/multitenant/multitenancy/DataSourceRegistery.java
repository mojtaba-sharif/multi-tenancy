package me.ramon.multitenant.multitenancy;

import java.util.HashMap;

public class DataSourceRegistery extends HashMap<String, DataSourceWrapper> {


    private static DataSourceRegistery dataSourceRegistery;

    private DataSourceRegistery() {
    }

    public static DataSourceRegistery getInstance() {
        if (dataSourceRegistery == null)
            dataSourceRegistery = new DataSourceRegistery();
        return dataSourceRegistery;
    }


}
