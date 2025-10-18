package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    @Description("Проверка регистрации с валидными данными. Ожидается успешная регистрация и переход на страницу логина")
    @Step("Проверка успешной регистрации пользователя с валидными данными")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        String testEmail = generateUniqueEmail();

        registerPage.register(USER_NAME, testEmail, VALID_PASSWORD);

        usersToCleanup.add(testEmail);

        assertTrue("Должна отображаться страница логина после регистрации",
                loginPage.isLoginPageLoaded());
    }

    /**
     * Проверяет ошибку при вводе слишком короткого пароля
     */
    @Test
    @DisplayName("Ошибка при вводе короткого пароля")
    @Description("Проверка регистрации с коротким паролем. Ожидается сообщение об ошибке валидации")
    @Step("Проверка ошибки валидации при вводе короткого пароля")
    public void registrationWithShortPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        String testEmail = generateUniqueEmail();

        registerPage.register(USER_NAME, testEmail, SHORT_PASSWORD);

        usersToCleanup.add(testEmail);

        String error = registerPage.getPasswordError();
        assertTrue("Должна отображаться ошибка о коротком пароле",
                error.contains("Некорректный пароль"));
    }
}