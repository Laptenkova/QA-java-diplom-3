package ru.yandex.praktikum.constants;

import java.util.UUID;

/**
 * Константы тестовых данных для UI-тестов.
 * Содержит валидные и невалидные значения, а также метод для генерации уникального email.
 */
public class TestConstants {
    /**
     * Валидный email существующего пользователя для тестов
     */
    public static final String VALID_EMAIL = "Ivanov_BurgerMaster@mail.ru";

    /**
     * Валидный пароль существующего пользователя
     */
    public static final String VALID_PASSWORD = "1111111";

    /**
     * Имя пользователя для регистрации
     */
    public static final String USER_NAME = "Иван";

    /**
     * Короткий пароль для проверки валидации поля (менее 6 символов)
     */
    public static final String SHORT_PASSWORD = "12345";

    /**
     * Несуществующий email для негативного тестирования
     */
    public static final String INVALID_EMAIL = "nonexistent@test.com";

    /**
     * Неверный пароль для проверки ошибок авторизации
     */
    public static final String INVALID_PASSWORD = "wrongpassword";

    /**
     * Генерирует уникальный email для тестовой регистрации.
     * Позволяет избежать конфликтов при параллельном запуске тестов.
     *
     * @return уникальный email в формате user_<UUID>@test.com
     */
    public static String generateUniqueEmail() {
        return "user_" + UUID.randomUUID() + "@test.com";
    }
}
