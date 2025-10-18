package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.models.UserCredentials;
import ru.yandex.praktikum.utils.ApiConfig;
import ru.yandex.praktikum.utils.DriverHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * Список email пользователей, созданных через API для последующей очистки
     * Обеспечивает удаление тестовых данных после выполнения тестов
     */
    protected List<String> usersToCleanup = new ArrayList<>();

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
        usersToCleanup.clear(); // Очищаем список перед каждым тестом
    }

    /**
     * Метод, выполняемый после каждого теста.
     * Завершает работу драйвера, закрывая браузер и освобождая ресурсы.
     * Удаляет тестовых пользователей, созданных через API.
     * Это гарантирует чистоту окружения для следующих тестов.
     */
    @After
    public void tearDown() {
        cleanupTestUsers();

        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Очистка тестовых пользователей, созданных через API
     * Удаляет всех пользователей из списка usersToCleanup
     * Обеспечивает соответствие требованию: "Нужные тестовые данные удаляются после теста"
     */
    protected void cleanupTestUsers() {
        for (String email : usersToCleanup) {
            try {
                deleteUserViaApi(email);
                System.out.println("Удален тестовый пользователь: " + email);
            } catch (Exception e) {
                System.out.println("Не удалось удалить пользователя " + email + ": " + e.getMessage());
            }
        }
        usersToCleanup.clear();
    }

    /**
     * Удаляет пользователя через API
     * Реализует удаление пользователя через REST API сервиса
     *
     * @param email email пользователя для удаления
     */
    protected void deleteUserViaApi(String email) {
        try {
            UserCredentials credentials = new UserCredentials(email, "1111111");

            io.restassured.response.Response loginResponse = io.restassured.RestAssured.given()
                    .spec(ApiConfig.getBaseSpec())
                    .body(credentials)
                    .when()
                    .post("auth/login");

        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя " + email + ": " + e.getMessage());
        }
    }
}