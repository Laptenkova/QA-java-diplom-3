package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.constants.TestConstants.*;

/**
 * Тесты функциональности регистрации пользователя
 * Использует BaseTest для инициализации WebDriver
 */
public class RegistrationTest extends BaseTest {

    /**
     * Проверяет успешную регистрацию нового пользователя с валидными данными
     */
    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        // Регистрируем пользователя с уникальным email
        registerPage.register(USER_NAME, generateUniqueEmail(), VALID_PASSWORD);

        // Проверяем, что появилась страница логина после регистрации
        assertTrue("Должна отображаться страница логина после регистрации",
                loginPage.isLoginPageLoaded());
    }

    /**
     * Проверяет ошибку при вводе слишком короткого пароля
     */
    @Test
    @DisplayName("Ошибка при вводе короткого пароля")
    public void registrationWithShortPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        // Пытаемся зарегистрироваться с коротким паролем
        registerPage.register(USER_NAME, generateUniqueEmail(), SHORT_PASSWORD);

        // Получаем текст ошибки и проверяем, что она корректна
        String error = registerPage.getPasswordError();
        assertTrue("Должна отображаться ошибка о коротком пароле",
                error.contains("Некорректный пароль"));
    }
}
