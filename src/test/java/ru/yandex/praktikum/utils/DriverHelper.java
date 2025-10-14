package ru.yandex.praktikum.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static ru.yandex.praktikum.utils.DriverConfig.*;

/**
 * Вспомогательный класс для инициализации и настройки WebDriver
 * Реализует паттерн Factory для создания драйверов разных браузеров
 */
public class DriverHelper {
    protected WebDriver driver;

    /**
     * Инициализирует и возвращает WebDriver на основе настроек из properties файла
     *
     * @return настроенный экземпляр WebDriver
     * @throws IOException если файл конфигурации не найден
     */
    public WebDriver initDriver() throws IOException {
        BrowserType browserType = getBrowserTypeFromProperties();

        switch (browserType) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case YANDEX:
                driver = createYandexDriver();
                break;
            default:
                throw new RuntimeException("Browser undefined: " + browserType);
        }

        configureDriver();

        return driver;
    }

    /**
     * Читает тип браузера из конфигурационного файла browser.properties
     *
     * @return тип браузера для тестирования
     * @throws IOException если файл конфигурации не найден
     */
    private BrowserType getBrowserTypeFromProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(BROWSER_PROPERTIES_PATH));
        String browserProperty = properties.getProperty("testBrowser");
        System.out.println("Browser property: " + browserProperty);

        return BrowserType.valueOf(browserProperty);
    }

    /**
     * Создает и возвращает ChromeDriver с настройками по умолчанию
     *
     * @return экземпляр ChromeDriver
     */
    private WebDriver createChromeDriver() {
        return new ChromeDriver();
    }

    /**
     * Создает и возвращает ChromeDriver для Яндекс Браузера
     * Настраивает путь к исполняемому файлу Яндекс Браузера
     *
     * @return экземпляр ChromeDriver настроенный для Яндекс Браузера
     */
    private WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", YANDEX_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();

        String yandexPath = findYandexBrowserPath();
        if (yandexPath != null) {
            options.setBinary(yandexPath);
        } else {
            throw new RuntimeException("Яндекс Браузер не найден на системе. Проверьте установку браузера.");
        }

        return new ChromeDriver(options);
    }

    /**
     * Ищет установленный Яндекс Браузер в системе
     * Проверяет все возможные пути установки для текущей ОС
     *
     * @return путь к исполняемому файлу Яндекс Браузера или null если не найден
     */
    private String findYandexBrowserPath() {
        for (String path : YANDEX_BROWSER_PATHS) {
            if (new File(path).exists()) {
                System.out.println("Yandex browser found at: " + path);
                return path;
            }
        }
        System.out.println("Yandex browser not found in any known location");

        return null;
    }

    /**
     * Настраивает общие параметры WebDriver:
     * - Таймауты ожидания
     * - Размер окна браузера
     * - Открывает базовый URL приложения
     */
    private void configureDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }
}