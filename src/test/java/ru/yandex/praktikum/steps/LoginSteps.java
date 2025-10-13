package ru.yandex.praktikum.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageobject.LoginPage;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginSteps {
    private final LoginPage loginPage;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(LoginSteps.class.getName());

    public LoginSteps(WebDriver driver) {
        this.loginPage = new LoginPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForLoginPage() {
        logger.info("Waiting for login page to load");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginButtonLocator()));
    }

    public void loginWithCredentials(String email, String password) {
        waitForLoginPage();
        logger.info("Entering user credentials");
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    public String getErrorMessage() {
        return loginPage.getErrorMessage();
    }
}
