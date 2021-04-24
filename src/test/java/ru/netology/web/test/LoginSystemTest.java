package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.DbInteraction;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

class LoginSystemTest {
    @BeforeEach
    void setUp() throws SQLException {
        DbInteraction.clearDatabase();
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginToTheSystem() throws SQLException {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        String code = DbInteraction.verificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldIncorrectPasswordEnteredThreeTimes() {
        val loginPage = new LoginPage();
        loginPage.invalidPassword();
        val verificationPage = loginPage.validLogin();
        verificationPage.verificationPageIsHidden();
    }

    @Test
    void shouldSubmittingEmptyForm() {
        val loginPage = new LoginPage();
        loginPage.emptyLogin();
    }

    @Test
    void shouldSendingEmptyCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        verificationPage.emptyVerify();
    }

    @Test
    void shouldEnterInvalidCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin();
        val invalidVerificationCode = DataHelper.getInvalidVerificationCode();
        verificationPage.invalidVerify(invalidVerificationCode);
    }

    @Test
    void shouldUnregisteredUserLogin() {
        val loginPage = new LoginPage();
        loginPage.invalidLogin();
    }
}

