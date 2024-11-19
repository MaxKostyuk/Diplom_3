package com.kotan4ik.utils;

import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestUtils {
    private static final String MAIL_BASE = "testUserName@test.com";
    private static final String NAME_BASE = "testUserName";
    private static final String VALID_PASSWORD = "12345678";

    @Step("Generating random user name and email and valid password")
    public static Map<String, String> generateRandomUserProperties() {
        Map<String, String> values = new HashMap<>();
        Random random = new Random();

        int randomPrefix = random.nextInt(1000000000);
        String testEmail = randomPrefix + MAIL_BASE;
        String testName = randomPrefix + NAME_BASE;

        values.put("email", testEmail);
        values.put("password", VALID_PASSWORD);
        values.put("name", testName);

        return values;
    }
}
