package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.val;
import com.github.javafaker.Faker;

import java.sql.*;

public class DbInteraction {

    @SneakyThrows
    public static Statement getStatement() {
        val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        val statement = conn.createStatement();
        return statement;
    }

    @SneakyThrows
    public static String verificationCode() {
        val statement = getStatement();
        {
            val rs = statement.executeQuery("SELECT * FROM auth_codes;");
            {
                if (rs.next()) {
                    val code = rs.getString("code");
                    return code;
                }
            }
        }
        return null;
    }

    @SneakyThrows
    public static void clearDatabase() {
        val statement = getStatement();
        String sql = "DELETE FROM  auth_codes;";
        statement.executeUpdate(sql);
    }

    static Faker faker = new Faker();

    public static String getInvalidLogin() {
        return faker.name().username();
    }

    public static String getInvalidPassword() {
        return faker.internet().password();
    }

    public static String getInvalidCode() {
        return faker.number().digits(4);
    }
}


