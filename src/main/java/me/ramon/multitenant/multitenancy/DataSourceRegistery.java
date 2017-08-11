package me.ramon.multitenant.multitenancy;

import java.util.HashMap;

/**
 * Created by Romon on 1/14/2017.
 */
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
