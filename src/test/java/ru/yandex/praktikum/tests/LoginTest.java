package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.praktikum.factory.DriverFactory;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.constants.TestConstants.*;

/**
 * Тесты для функциональности входа в систему
 */
public class LoginTest {

    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    @DisplayName("Успешный вход через кнопку 'Войти в аккаунт' на главной")
    public void testSuccessfulLoginViaMainPage() {
        // Шаг 1: Открыть главную страницу и перейти к входу
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        assertTrue("Главная страница должна быть загружена",
                mainPage.isMainPageLoaded());

        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Страница входа должна быть загружена",
                loginPage.isLoginPageLoaded());

        // Шаг 2: Выполнить вход с валидными данными
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        // Шаг 3: Проверить успешность входа (редирект на главную)
        assertTrue("После успешного входа должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Успешный вход через кнопку 'Личный кабинет'")
    public void testSuccessfulLoginViaPersonalAccount() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        assertTrue("Главная страница должна быть загружена",
                mainPage.isMainPageLoaded());

        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        assertTrue("Страница входа должна быть загружена",
                loginPage.isLoginPageLoaded());

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        assertTrue("После успешного входа должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Вход с неверным паролем")
    public void testLoginWithInvalidPassword() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Страница входа должна быть загружена",
                loginPage.isLoginPageLoaded());

        // Шаг 1: Ввести валидный email и неверный пароль
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(INVALID_PASSWORD); // ✅ Используем константу
        loginPage.clickLoginButton();

        // Шаг 2: Проверить что сообщение об ошибке отображается
        String errorMessage = loginPage.getErrorMessage();
        assertTrue("Должно отображаться сообщение об ошибке при неверном пароле",
                !errorMessage.isEmpty());
    }

    @Test
    @DisplayName("Вход с неверным email")
    public void testLoginWithInvalidEmail() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Страница входа должна быть загружена",
                loginPage.isLoginPageLoaded());

        // Шаг 1: Ввести неверный email и валидный пароль
        loginPage.enterEmail(INVALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        // Шаг 2: Проверить что сообщение об ошибке отображается
        String errorMessage = loginPage.getErrorMessage();
        assertTrue("Должно отображаться сообщение об ошибке при неверном email",
                !errorMessage.isEmpty());
    }

    @Test
    @DisplayName("Переход со страницы регистрации на страницу входа")
    public void testNavigateFromRegisterToLogin() {
        // Шаг 1: Перейти на страницу регистрации
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        assertTrue("Страница регистрации должна быть загружена",
                registerPage.isRegisterPageLoaded());

        // Шаг 2: Вернуться на страницу входа
        LoginPage returnedLoginPage = registerPage.clickLoginLink();

        // Шаг 3: Проверить что мы на странице входа
        assertTrue("Должна отображаться страница входа после перехода с регистрации",
                returnedLoginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Переход на страницу восстановления пароля")
    public void testNavigateToPasswordRecovery() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Страница входа должна быть загружена",
                loginPage.isLoginPageLoaded());

        // Шаг 1: Кликнуть на ссылку восстановления пароля
        loginPage.clickRecoverPasswordLink();

        // Шаг 2: Здесь можно добавить проверку что перешли на страницу восстановления
        // (потребуется создать соответствующий Page Object)
    }
}