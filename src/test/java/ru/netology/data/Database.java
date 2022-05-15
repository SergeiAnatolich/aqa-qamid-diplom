package ru.netology.data;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@UtilityClass
public class Database {

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "sergei", "password");
    }

    @SneakyThrows
    private String getCreditStatus() {
        var creditStatusQuery = "SELECT status FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("status");
            }
        }
    }

    @SneakyThrows
    private String getBankIdBuyInCredit() {
        var creditStatusQuery = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("bank_id");
            }
        }
    }

    @SneakyThrows
    private String getCreditId() {
        var creditStatusQuery = "SELECT credit_id FROM order_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("credit_id");
            }
        }
    }

    @SneakyThrows
    private String getPaymentId() {
        var creditStatusQuery = "SELECT payment_id FROM order_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("payment_id");
            }
        }
    }

    @SneakyThrows
    private String getAmount() {
        var creditStatusQuery = "SELECT amount FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("amount");
            }
        }
    }

    @SneakyThrows
    private String getPaymentStatus() {
        var creditStatusQuery = "SELECT status FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("status");
            }
        }
    }

    @SneakyThrows
    private String getTransactionId() {
        var creditStatusQuery = "SELECT transaction_id FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(creditStatusQuery)) {
                rs.next();
                return rs.getString("transaction_id");
            }
        }
    }

    public void checkSuccessfullyBuyTourOnCard() {
        assertEquals("4500000", getAmount());
        assertEquals("APPROVED", getPaymentStatus());
        assertEquals(getTransactionId(), getPaymentId());
        assertNull(getCreditId());
    }

    public void checkDeclinedBuyTourOnCard() {
        assertEquals("4500000", getAmount());
        assertEquals("DECLINED", getPaymentStatus());
        assertEquals(getTransactionId(), getPaymentId());
        assertNull(getCreditId());
    }
}