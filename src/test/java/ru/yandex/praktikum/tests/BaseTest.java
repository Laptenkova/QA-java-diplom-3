package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.utils.DriverHelper;

import java.io.IOException;

/**
 * Базовый класс для всех UI тестов.
 * Обеспечивает инициализацию и завершение работы WebDriver.
 * Используется всеми классами тестов в проекте.
 */
public class BaseTest {
    /**
     * Экземпляр WebDriver для управления браузером.
     * Доступен в наследниках для взаимодействия с веб-страницей.
     */
    protected WebDriver driver;

    /**
     * Метод, выполняемый перед каждым тестом.
     * Инициализирует драйвер на основе настроек из файла browser.properties.
     *
     * @throws IOException если файл конфигурации не найден или не читается.
     */
    @Before
    public void startUp() throws IOException {
        DriverHelper driverHelper = new DriverHelper();
        driver = driverHelper.initDriver();
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Завершает работу драйвера, закрывая браузер и освобождая ресурсы.
     * Это гарантирует чистоту окружения для следующих тестов.
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Полностью закрывает браузер и завершает сессию
        }
    }
}
