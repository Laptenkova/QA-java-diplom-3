package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.factory.DriverFactory;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.constants.TestConstants.VALID_EMAIL;
import static ru.yandex.praktikum.constants.TestConstants.VALID_PASSWORD;

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
    public void loginViaAccountButtonTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    @Test
    public void loginViaPersonalAccountTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        assertTrue("Должна отображаться страница логина", loginPage.isLoginPageLoaded());
    }

    @Test
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        LoginPage returnedLoginPage = registerPage.clickLoginLink();
        assertTrue("Должна отображаться страница логина", returnedLoginPage.isLoginPageLoaded());
    }

    @Test
    public void successfulLoginTest() {
        MainPage mainPage = new MainPage(driverFactory.getDriver());
        LoginPage loginPage = mainPage.clickLoginAccountButton();

        loginPage.setEmail(VALID_EMAIL);
        loginPage.setPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();

        assertTrue("После успешного входа должна отображаться главная страница", mainPage.isMainPageLoaded());
    }
}
