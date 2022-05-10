package ru.netology.data;

import lombok.Value;

public class DataHelper {

    @Value
    public static class Card {
        String numberCard;
        String monthCard;
        String yearCard;
        String cardHolder;
        String cvcCard;
    }

    public static Card getValidRegisteredCard() {
        return new Card(
                "1111222233334444",
                "06",
                "25",
                "IVAN IVANOV",
                "123"
        );
    }

    public static Card getValidRegisteredCardForCredit() {
        return new Card(
                "5555666677778888",
                "05",
                "25",
                "IVAN IVANOV",
                "123"
        );
    }

    public static Card getValidUnregisteredCard() {
        return new Card(
                "1234123412341234",
                "12",
                "25",
                "IVAN IVANOV",
                "123"
        );
    }

    public static Card getInvalidCard() {
        return new Card(
                "123412341234123",
                "17",
                "00",
                "IVAN123 IVANOV123",
                "00"
        );
    }
}