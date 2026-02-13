package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.api.UserApi;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.constants.TestConstants.INVALID_PASSWORD;
import static ru.yandex.praktikum.constants.TestConstants.VALID_PASSWORD;

/**
 * Тесты функциональности входа в систему
 * Наследует от BaseTest для автоматической инициализации WebDriver
 */
public class LoginTest extends BaseTest {

    /**
     * Проверяет вход в систему по кнопке "Войти в аккаунт" на главной странице
     */
    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной странице")
    @Description("Проверка авторизации через кнопку 'Войти в аккаунт'. Ожидается успешный вход в систему")
    @Step("Проверка авторизации через кнопку 'Войти в аккаунт'")
    public void loginViaAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        String testUserEmail = UserApi.createUser();

        usersToCleanup.add(testUserEmail);

        loginPage.setEmail(testUserEmail);
        loginPage.setPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("После успешной авторизации должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    /**
     * Проверяет вход через кнопку "Личный кабинет" на главной странице
     */
    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка авторизации через кнопку 'Личный кабинет'. Ожидается успешный вход в систему")
    @Step("Проверка авторизации через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        String testUserEmail = UserApi.createUser();

        usersToCleanup.add(testUserEmail);

        loginPage.setEmail(testUserEmail);
        loginPage.setPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("После успешной авторизации должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    /**
     * Проверяет переход с формы регистрации на страницу логина
     */
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка перехода на страницу логина со страницы регистрации. Ожидается загрузка страницы авторизации")
    @Step("Проверка перехода на страницу логина с формы регистрации")
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        LoginPage returnedLoginPage = registerPage.clickLoginLink();
        assertTrue("Должна отображаться страница логина", returnedLoginPage.isLoginPageLoaded());
    }

    /**
     * Проверяет возможность перехода со страницы восстановления пароля на страницу логина
     */
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка навигации между страницей восстановления пароля и логина. Ожидается корректный переход между страницами")
    @Step("Проверка перехода на страницу логина со страницы восстановления пароля")
    public void loginFromPasswordRecoveryPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        loginPage.clickRecoverPasswordLink();

        assertTrue("Должна отображаться страница восстановления пароля",
                driver.getCurrentUrl().contains("/forgot-password"));

        driver.navigate().back();
    }

    /**
     * Проверяет успешный вход с валидными учетными данными
     * Пользователь создается через API перед тестом
     */
    @Test
    @DisplayName("Успешный вход с валидными данными")
    @Description("Проверка авторизации с валидными данными. Ожидается успешный вход и переход на главную страницу")
    @Step("Проверка успешного входа с валидными учетными данными")
    public void successfulLoginTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        String testUserEmail = UserApi.createUser();

        usersToCleanup.add(testUserEmail);
        loginPage.setEmail(testUserEmail);
        loginPage.setPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("После успешного входа должна отображаться главная страница",
                mainPage.isMainPageLoaded());
    }

    /**
     * Проверяет, что при вводе неверного пароля появляется сообщение об ошибке
     * Пользователь создается через API перед тестом
     */
    @Test
    @DisplayName("Ошибка при входе с неверным паролем")
    @Description("Проверка авторизации с неверным паролем. Ожидается сообщение об ошибке")
    @Step("Проверка ошибки авторизации при вводе неверного пароля")
    public void loginWithInvalidPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        String testUserEmail = UserApi.createUser();
        usersToCleanup.add(testUserEmail);

        loginPage.setEmail(testUserEmail);
        loginPage.setPassword(INVALID_PASSWORD);
        loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        assertTrue("Должна отображаться ошибка при неверном пароле",
                !error.isEmpty());
    }
}