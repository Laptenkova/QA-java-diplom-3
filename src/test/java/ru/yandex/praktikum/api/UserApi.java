package ru.yandex.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.models.User;
import ru.yandex.praktikum.utils.ApiConfig;

import static ru.yandex.praktikum.constants.TestConstants.*;

/**
 * Класс для работы с API пользователей
 * Содержит методы для создания, удаления и управления пользователями через API
 */
public class UserApi {

    /**
     * Создает пользователя через API и возвращает email
     * Использует сериализацию объекта User для формирования JSON
     *
     * @return email созданного пользователя
     */
    @Step("Создание тестового пользователя через API")
    public static String createUser() {
        String email = generateUniqueEmail();

        User user = new User(email, VALID_PASSWORD, USER_NAME);

        Response response = io.restassured.RestAssured.given()
                .spec(ApiConfig.getBaseSpec())
                .body(user)
                .when()
                .post("auth/register");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя через API: " + response.getBody().asString());
        }

        return email;
    }

    /**
     * Создает пользователя с кастомными данными через API
     *
     * @param email email пользователя
     * @param password пароль пользователя
     * @param name имя пользователя
     * @return email созданного пользователя
     */
    @Step("Создание тестового пользователя через API с кастомными данными")
    public static String createUser(String email, String password, String name) {
        User user = new User(email, password, name);

        Response response = io.restassured.RestAssured.given()
                .spec(ApiConfig.getBaseSpec())
                .body(user)
                .when()
                .post("auth/register");

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Не удалось создать пользователя через API: " + response.getBody().asString());
        }

        return email;
    }

    /**
     * Удаляет пользователя через API
     *
     * @param email email пользователя для удаления
     */
    @Step("Удаление пользователя через API")
    public static void deleteUser(String email) {
        try {
            ru.yandex.praktikum.models.UserCredentials credentials =
                    new ru.yandex.praktikum.models.UserCredentials(email, VALID_PASSWORD);

            // Получаем токен для удаления
            Response loginResponse = io.restassured.RestAssured.given()
                    .spec(ApiConfig.getBaseSpec())
                    .body(credentials)
                    .when()
                    .post("auth/login");

            if (loginResponse.getStatusCode() == 200) {
                String token = loginResponse.jsonPath().getString("accessToken");

                if (token != null && !token.isEmpty()) {
                    // Удаляем пользователя с использованием токена
                    io.restassured.RestAssured.given()
                            .spec(ApiConfig.getBaseSpec())
                            .header("Authorization", token)
                            .when()
                            .delete("auth/user")
                            .then()
                            .statusCode(202);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при удалении пользователя " + email + ": " + e.getMessage());
        }
    }
}