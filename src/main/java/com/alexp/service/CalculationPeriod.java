package com.alexp.service;

import java.util.Calendar;

public enum CalculationPeriod {
    HOUR(Calendar.HOUR),
    DAY(Calendar.DAY_OF_MONTH),
    WEEK(Calendar.WEEK_OF_MONTH),
    MONTH(Calendar.MONTH),
    YEAR(Calendar.YEAR);

    private final int value;

    CalculationPeriod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
