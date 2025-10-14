package ru.yandex.praktikum.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
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
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginViaAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    /**
     * Проверяет вход через кнопку "Личный кабинет" на главной странице
     */
    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    /**
     * Проверяет переход с формы регистрации на страницу логина
     */
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
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
    public void loginFromPasswordRecoveryPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();

        // Переходим на страницу восстановления пароля
        loginPage.clickRecoverPasswordLink();

        // Проверяем что перешли на страницу восстановления
        assertTrue("Должна отображаться страница восстановления пароля",
                driver.getCurrentUrl().contains("/forgot-password"));

        // Возвращаемся назад на страницу логина
        driver.navigate().back();
    }

    /**
     * Проверяет успешный вход с валидными учетными данными
     * Пользователь создается через API перед тестом
     */
    @Test
    @DisplayName("Успешный вход с валидными данными")
    public void successfulLoginTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        // Создаем пользователя через API
        String testUserEmail = loginPage.createUserViaApi();

        // Добавляем пользователя в список для очистки после теста
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
    public void loginWithInvalidPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        // Создаем пользователя через API
        String testUserEmail = loginPage.createUserViaApi();
        // Добавляем пользователя в список для очистки после теста
        usersToCleanup.add(testUserEmail);

        loginPage.setEmail(testUserEmail);
        loginPage.setPassword(INVALID_PASSWORD);
        loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        assertTrue("Должна отображаться ошибка при неверном пароле",
                !error.isEmpty());
    }
}