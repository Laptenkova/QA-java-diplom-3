package ru.yandex.praktikum.utils;

/**
 * Конфигурационный класс для настроек WebDriver и тестовой среды
 * Содержит константы для URL и таймаутов ожидания
 */
public class DriverConfig {
    /**
     * Базовый URL тестируемого приложения Stellar Burgers
     */
    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    /**
     * Таймаут неявного ожидания элементов (секунды)
     * WebDriver будет ждать появления элемента перед выбрасыванием исключения
     */
    public static final long IMPLICIT_WAIT_SECONDS = 5;

    /**
     * Таймаут загрузки страницы (секунды)
     * Максимальное время ожидания полной загрузки страницы
     */
    public static final long PAGE_LOAD_TIMEOUT_SECONDS = 10;

    /**
     * Путь к исполняемому файлу драйвера Яндекс Браузера
     */
    public static final String YANDEX_DRIVER_PATH = "src/test/resources/yandexdriver";

    /**
     * Путь к файлу конфигурации браузера
     */
    public static final String BROWSER_PROPERTIES_PATH = "src/test/resources/browser.properties";

    /**
     * Возможные пути установки Яндекс Браузера на разных ОС
     * Используется для автоматического определения расположения браузера в системе
     */
    public static final String[] YANDEX_BROWSER_PATHS = {
            "/Applications/Yandex.app/Contents/MacOS/Yandex",  // macOS стандартный путь
            "/Applications/Yandex Browser.app/Contents/MacOS/Yandex",  // macOS альтернативный путь
            "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe"  // Windows путь
    };
}