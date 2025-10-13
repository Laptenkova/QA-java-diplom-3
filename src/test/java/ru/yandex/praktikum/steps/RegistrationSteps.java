package ru.yandex.praktikum.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageobject.RegisterPage;

import java.time.Duration;
import java.util.logging.Logger;

public class RegistrationSteps {
    private final RegisterPage registerPage;
    private final WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(RegistrationSteps.class.getName());

    public RegistrationSteps(WebDriver driver) {
        this.registerPage = new RegisterPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForRegisterPage() {
        logger.info("Ожидание загрузки страницы регистрации");
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerPage.getRegisterButtonLocator()));

    }

    public void register(String name, String email, String password) {
        waitForRegisterPage();
        logger.info(String.format("Регистрация с данными: %s, %s, %s", name, email, password));
        registerPage.enterName(name);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.clickRegisterButton();
    }

    public String getPasswordError() {
        return registerPage.getPasswordError();
    }
}
