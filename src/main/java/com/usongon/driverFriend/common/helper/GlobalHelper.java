package com.usongon.driverFriend.common.helper;

public class GlobalHelper {
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    public static <T> T get() {
        return (T) threadLocal.get();
    }

    public static void set(Object object) {
        threadLocal.set(object);
    }
}
