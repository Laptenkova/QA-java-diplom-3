package ru.yandex.praktikum.constants;

import java.util.UUID;

public class TestConstants {
    public static final String VALID_EMAIL = "Ivanov_BurgerMaster@mail.ru";
    public static final String VALID_PASSWORD = "1111111";
    public static final String USER_NAME = "Иван";
    public static final String SHORT_PASSWORD = "12345";

    public static final String INVALID_EMAIL = "nonexistent@test.com";
    public static final String INVALID_PASSWORD = "wrongpassword";

    // Метод для генерации уникального email
    public static String generateUniqueEmail() {
        return "user_" + UUID.randomUUID() + "@test.com";
    }
}
