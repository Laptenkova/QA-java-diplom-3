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

public class RegistrationTest {
    private DriverFactory driverFactory;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driverFactory = new DriverFactory();
        mainPage = new MainPage(driverFactory.getDriver());
    }

    @After
    public void tearDown() {
        if (driverFactory != null) {
            driverFactory.quitDriver();
        }
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() {
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.enterName(USER_NAME);
        registerPage.enterEmail("unique" + System.currentTimeMillis() + "@test.com");
        registerPage.enterPassword(VALID_PASSWORD);
        registerPage.clickRegisterButton();

        assertTrue("Должна отображаться страница логина после регистрации",
                loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Ошибка при вводе короткого пароля")
    public void registrationWithShortPasswordTest() {
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.enterName(USER_NAME);
        registerPage.enterEmail("test@example.com");
        registerPage.enterPassword(SHORT_PASSWORD);
        registerPage.clickRegisterButton();

        String error = registerPage.getPasswordError();
        assertTrue("Должна отображаться ошибка о коротком пароле",
                error.contains("Некорректный пароль"));
    }
}