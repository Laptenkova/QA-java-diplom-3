package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By loginAccountButton = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    private final By personalAccountButton = By.cssSelector("a[href='/account'] p");

    private final By bunsSection = By.xpath("//span[text()='Булки']");
    private final By saucesSection = By.xpath("//span[text()='Соусы']");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']");

    private final By currentSection = By.cssSelector("div.tab_tab_type_current__2BEPc");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private void clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    private boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    @Step("Нажатие кнопки 'Войти в аккаунт'")
    public LoginPage clickLoginAccountButton() {
        clickElement(loginAccountButton);
        return new LoginPage(driver);
    }

    @Step("Нажатие кнопки 'Личный кабинет'")
    public LoginPage clickPersonalAccountButton() {
        clickElement(personalAccountButton);
        return new LoginPage(driver);
    }

    @Step("Нажатие раздела 'Булки'")
    public void clickBunsSection() {
        clickElement(bunsSection);
    }

    @Step("Нажатие раздела 'Соусы'")
    public void clickSaucesSection() {
        clickElement(saucesSection);
    }

    @Step("Нажатие раздела 'Начинки'")
    public void clickFillingsSection() {
        clickElement(fillingsSection);
    }

    @Step("Получение текста активного раздела")
    public String getCurrentSectionText() {
        return getText(currentSection);
    }

    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageLoaded() {
        return isDisplayed(By.xpath("//h1[text()='Соберите бургер']"));
    }

    public By getCurrentSectionLocator() {
        return currentSection;
    }
}