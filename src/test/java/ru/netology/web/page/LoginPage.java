package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement emptyLogin = $("[data-test-id=login] .input__sub");
    private SelenideElement emptyPassword = $("[data-test-id=password] .input__sub");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public void fieldСleaning() {
        loginField.doubleClick().sendKeys(Keys.DELETE);
        passwordField.doubleClick().sendKeys(Keys.DELETE);
    }

    public VerificationPage validLogin() {
        fieldСleaning();
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getAuthInfo().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin() {
        fieldСleaning();
        loginField.setValue(DataHelper.getInvalidAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getInvalidAuthInfo().getPassword());
        loginButton.click();
        errorNotification.shouldBe(visible);
    }

    public void invalidPassword() {
        fieldСleaning();
        loginField.setValue(DataHelper.getAuthInfo().getLogin());
        passwordField.setValue(DataHelper.getInvalidAuthInfo().getPassword());
        loginButton.click();
        loginButton.click();
        loginButton.click();
    }

    public void emptyLogin() {
        loginButton.click();
        emptyLogin.shouldBe(visible);
        emptyPassword.shouldBe(visible);
    }
}
