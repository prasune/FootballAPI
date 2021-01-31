package com.test.league.football.controller.utils;

import com.test.league.football.exception.BadRequestException;

public class ValidationUtil {
    public static void rejectIfEmpty(String fieldName, String value) {
        if (value == null || value.isEmpty()) {
            throw new BadRequestException(fieldName + " must not be blank");
        }
    }
}
