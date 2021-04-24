package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("vasya1", "qwerty1234");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("12347");
    }
}