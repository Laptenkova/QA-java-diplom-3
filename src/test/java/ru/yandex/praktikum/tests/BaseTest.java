package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
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
        // Очистка тестовых пользователей перед закрытием браузера
        cleanupTestUsers();

        if (driver != null) {
            driver.quit();  // Полностью закрывает браузер и завершает сессию
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
            // Сначала получаем токен через логин
            String loginBody = String.format(
                    "{\"email\": \"%s\", \"password\": \"%s\"}",
                    email, "1111111" // Пароль тестового пользователя
            );

            io.restassured.response.Response loginResponse = io.restassured.RestAssured.given()
                    .header("Content-type", "application/json")
                    .baseUri("https://stellarburgers.education-services.ru/api/")
                    .body(loginBody)
                    .when()
                    .post("auth/login");

            if (loginResponse.getStatusCode() == 200) {
                String token = loginResponse.then().extract().path("accessToken");

                // Удаляем пользователя с использованием токена
                io.restassured.response.Response deleteResponse = io.restassured.RestAssured.given()
                        .header("Authorization", token)
                        .baseUri("https://stellarburgers.education-services.ru/api/")
                        .when()
                        .delete("auth/user");

                if (deleteResponse.getStatusCode() != 202) {
                    System.out.println("Не удалось удалить пользователя: " + deleteResponse.getBody().asString());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя " + email + ": " + e.getMessage());
        }
    }
}