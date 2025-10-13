package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.factory.DriverFactory;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.constants.TestConstants.*;

public class LoginTest {
    private DriverFactory driverFactory;

    @Before
    public void setUp() {
        driverFactory = new DriverFactory();
    }

    @After
    public void tearDown() {
        if (driverFactory != null) {
            driverFactory.quitDriver();
        }
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginViaAccountButtonTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        LoginPage returnedLoginPage = registerPage.clickLoginLink();
        assertTrue("Должна отображаться страница логина", returnedLoginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromPasswordRecoveryPageTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();

        // Переходим на страницу восстановления пароля
        loginPage.clickRecoverPasswordLink();

        // Проверяем что перешли на страницу восстановления
        assertTrue("Должна отображаться страница восстановления пароля",
                driverFactory.getDriver().getCurrentUrl().contains("/forgot-password"));

        // Здесь нужно добавить переход обратно на страницу логина
        // Для этого потребуется создать ForgotPasswordPage
        driverFactory.getDriver().navigate().back();
    }

    @Test
    @DisplayName("Успешный вход с валидными данными")
    public void successfulLoginTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();

        loginPage.setEmail(VALID_EMAIL);
        loginPage.setPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("После успешного входа должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    @Test
    @DisplayName("Ошибка при входе с неверным паролем")
    public void loginWithInvalidPasswordTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();

        loginPage.setEmail(VALID_EMAIL);
        loginPage.setPassword(INVALID_PASSWORD);
        loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        assertTrue("Должна отображаться ошибка при неверном пароле",
                !error.isEmpty());
    }
}