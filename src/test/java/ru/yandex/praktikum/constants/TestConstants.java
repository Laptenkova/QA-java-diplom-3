package ru.yandex.praktikum.constants;

import net.datafaker.Faker;

/**
 * Константы тестовых данных для UI-тестов.
 * Содержит валидные и невалидные значения, а также метод для генерации уникального email.
 */
public class TestConstants {
    /**
     * Генератор тестовых данных для создания реалистичных пользовательских данных
     */
    private static final Faker faker = new Faker();

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
     * Неверный пароль для проверки ошибок авторизации
     */
    public static final String INVALID_PASSWORD = "12345";

    /**
     * Генерирует уникальный email для тестовой регистрации.
     * Использует DataFaker для создания более реалистичных тестовых данных.
     *
     * @return уникальный email
     */
    public static String generateUniqueEmail() {
        return faker.internet().emailAddress();
    }
}