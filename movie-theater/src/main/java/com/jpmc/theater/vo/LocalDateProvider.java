package com.jpmc.theater.vo;

import java.time.LocalDate;


public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    private LocalDateProvider(){}
    /**
     * @return make sure to return singleton instance
     */
    public static synchronized LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }

    public LocalDate currentDate() {
        return LocalDate.now();
    }
}
