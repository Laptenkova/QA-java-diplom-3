package ru.yandex.praktikum.utils;

/**
 * Конфигурационный класс для настроек WebDriver и тестовой среды
 */
public class DriverConfig {
    // Браузеры
    public static final String BROWSER = "browser";
    public static final String DEFAULT_BROWSER = "chrome";
    public static final String FIREFOX_BROWSER = "firefox";

    // URL для Stellar Burgers
    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Таймауты
    public static final long IMPLICIT_WAIT_SECONDS = 5;
    public static final long PAGE_LOAD_TIMEOUT_SECONDS = 10;
}