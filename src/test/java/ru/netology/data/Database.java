package ru.netology.data;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@UtilityClass
public class Database {

    private Connection connection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "sergei", "password");
    }

    @SneakyThrows
    private String getCreditStatus() {
        var query = "SELECT status FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("status");
            }
        }
    }

    @SneakyThrows
    private String getCreatedCreditRequestEntity() {
        var query = "SELECT created FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("created").substring(0, 19);
            }
        }
    }

    @SneakyThrows
    private String getBankIdBuyInCredit() {
        var query = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("bank_id");
            }
        }
    }

    @SneakyThrows
    private String getIdInCredit() {
        var query = "SELECT id FROM credit_request_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("id");
            }
        }
    }

    @SneakyThrows
    private String getCreatedOrderEntity() {
        var query = "SELECT created FROM order_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("created").substring(0, 19);
            }
        }
    }

    @SneakyThrows
    private String getCreditId() {
        var query = "SELECT credit_id FROM order_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("credit_id");
            }
        }
    }

    @SneakyThrows
    private String getPaymentId() {
        var query = "SELECT payment_id FROM order_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("payment_id");
            }
        }
    }

    @SneakyThrows
    private String getAmount() {
        var query = "SELECT amount FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("amount");
            }
        }
    }

    @SneakyThrows
    private String getCreatedPaymentEntity() {
        var query = "SELECT created FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("created").substring(0, 19);
            }
        }
    }

    @SneakyThrows
    private String getPaymentStatus() {
        var query = "SELECT status FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("status");
            }
        }
    }

    @SneakyThrows
    private String getTransactionId() {
        var query = "SELECT transaction_id FROM payment_entity ORDER BY created DESC;";
        try (var statement = connection().createStatement()) {
            try (var rs = statement.executeQuery(query)) {
                rs.next();
                return rs.getString("transaction_id");
            }
        }
    }

    public void checkSuccessfullyBuyTourOnCard() {
        assertEquals("APPROVED", getPaymentStatus());
        assertEquals("4500000", getAmount());
        assertEquals(getCreatedPaymentEntity(), getCreatedOrderEntity());
        assertEquals(getTransactionId(), getPaymentId());
        assertNull(getCreditId());
    }

    public void checkDeclinedBuyTourOnCard() {
        assertEquals("DECLINED", getPaymentStatus());
        assertEquals("4500000", getAmount());
        assertEquals(getCreatedPaymentEntity(), getCreatedOrderEntity());
        assertEquals(getTransactionId(), getPaymentId());
        assertNull(getCreditId());
    }

    public void checkSuccessfullyBuyTourOnCredit() {
        assertEquals("APPROVED", getCreditStatus());
        assertEquals(getBankIdBuyInCredit(), getPaymentId());
        assertEquals(getCreatedCreditRequestEntity(), getCreatedOrderEntity());
        assertNotNull(getCreditId());
        assertEquals(getPaymentId(), getTransactionId());
        assertEquals("APPROVED", getPaymentStatus());
        assertEquals("4500000", getAmount());
    }

    public void checkDeclinedBuyTourOnCredit() {
        assertEquals("DECLINED", getCreditStatus());
        assertNull(getBankIdBuyInCredit());
        assertEquals(getCreatedCreditRequestEntity(), getCreatedOrderEntity());
        assertEquals(getIdInCredit(), getCreditId());
        assertNull(getPaymentId());
        assertNotEquals(getCreatedCreditRequestEntity(), getCreatedPaymentEntity());
    }
}