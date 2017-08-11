package me.ramon.multitenant.multitenancy;

/**
 * Created by Romon on 1/12/2017.
 */
public class TenantContext {
    static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static Object getCurrent() {
        return threadLocal.get();
    }

    public static void setCurrent(Object current) {
        threadLocal.set(current);
    }
}
