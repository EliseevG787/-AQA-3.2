package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DbInteraction;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

class LoginSystemTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static  void clean() {
       DbInteraction.cleanDatabase();
    }

    String login = DataHelper.getAuthInfo().getLogin();
    String password = DataHelper.getAuthInfo().getPassword();
    String invalidLogin = DbInteraction.getInvalidLogin();
    String invalidPassword = DbInteraction.getInvalidPassword();

    @Test
    void shouldLoginToTheSystem() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogIn(login, password);
        val code = DbInteraction.verificationCode();
        verificationPage.validVerify(code);
    }

    @Test
    void shouldSystemIsBlocked() {
        val loginPage = new LoginPage();
        loginPage.invalidLogIn(login, invalidPassword);
        loginPage.invalidLogIn(login, invalidPassword);
        loginPage.invalidLogIn(login, invalidPassword);
        loginPage.blockingMessage();
    }

    @Test
    void shouldSubmittingEmptyForm() {
        val loginPage = new LoginPage();
        loginPage.emptyLogIn();
    }

    @Test
    void shouldSendingEmptyCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogIn(login, password);
        verificationPage.emptyVerify();
    }

    @Test
    void shouldEnterInvalidCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogIn(login, password);
        verificationPage.invalidVerify(DbInteraction.getInvalidCode());
    }

    @Test
    void shouldUnregisteredUserLogin() {
        val loginPage = new LoginPage();
        loginPage.invalidLogIn(invalidLogin, invalidPassword);
    }
}

