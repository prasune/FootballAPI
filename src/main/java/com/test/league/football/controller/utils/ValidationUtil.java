package com.test.league.football.controller.utils;

public class ValidationUtil {
    public static void rejectIfEmpty(String fieldName, String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
}
