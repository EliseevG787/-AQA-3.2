package ru.netology;

import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInteraction {

    public static Statement getStatement() throws SQLException {
        val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        val statement = conn.createStatement();
        return statement;
    }

    public static String verificationCode() throws SQLException {
        try (
                val statement = getStatement();
        ) {
            try (val rs = statement.executeQuery("SELECT * FROM auth_codes;")) {
                if (rs.next()) {
                    val code = rs.getString("code");
                    return code;
                }
            }
        }
        return null;
    }

    public static void clearDatabase() throws SQLException {
        try (
                val statement = getStatement();
        )
        {
            try {
                statement.executeUpdate("DELETE FROM  auth_codes;");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

