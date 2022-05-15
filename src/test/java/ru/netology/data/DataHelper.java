package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker fakerEn = new Faker(new Locale("en"));
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    public static Card getValidRegisteredCard() {
        return new Card(
                "1111222233334444",
                getMonth(fakerEn.number().numberBetween(1, 12)),
                getYear(fakerEn.number().numberBetween(1, 4)),
                fakerEn.name().firstName() + " " + fakerEn.name().lastName(),
                fakerEn.number().digits(3)
        );
    }

    public static Card getValidRegisteredCardForCredit() {
        return new Card(
                "5555666677778888",
                getMonth(fakerEn.number().numberBetween(1, 12)),
                getYear(fakerEn.number().numberBetween(1, 4)),
                fakerEn.name().firstName() + " " + fakerEn.name().lastName(),
                fakerEn.number().digits(3)
        );
    }

    public static Card getValidUnregisteredCard() {
        return new Card(
                fakerEn.number().digits(16),
                getMonth(fakerEn.number().numberBetween(1, 12)),
                getYear(fakerEn.number().numberBetween(1, 4)),
                fakerEn.name().firstName() + " " + fakerEn.name().lastName(),
                fakerEn.number().digits(3)
        );
    }

    public static Card getInvalidCard() {
        return new Card(
                fakerEn.number().digits(fakerEn.number().numberBetween(2, 14)),
                fakerEn.number().digit(),
                fakerEn.number().digit(),
                fakerEn.number().digits(5),
                fakerEn.number().digits(2)
        );
    }

    public static String getMonth(int plusMonth) {
        return LocalDate.now().plusMonths(plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear(int plusYear) {
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLatinFirstName() {
        return fakerEn.name().firstName();
    }

    public static String getLatinLastName() {
        return fakerEn.name().lastName();
    }

    public static String getCyrillicFirstName() {
        return fakerRu.name().firstName();
    }

    public static String getLatin30Letters() {
        return fakerEn.letterify("??????????????????????????????");
    }

    public static String getActualYearWithChangeMonth(int plusMonth, int plusYear) {
        return LocalDate.now().plusMonths(plusMonth).plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getOneDigit() {
        return fakerEn.number().digit();
    }

    public static String getTwoDigits() {
        return fakerEn.number().digits(2);
    }

    public static String getThreeDigits() {
        return fakerEn.number().digits(3);
    }

    public static String getFourDigits() {
        return fakerEn.number().digits(4);
    }

    public static String get15Digits() {
        return fakerEn.number().digits(15);
    }

    public static String get20Digits() {
        return fakerEn.number().digits(4) + " " +
                fakerEn.number().digits(4) + " " +
                fakerEn.number().digits(4) + " " +
                fakerEn.number().digits(4) + " " +
                fakerEn.number().digits(4);
    }

    @Value
    public static class Card {
        String numberCard;
        String monthCard;
        String yearCard;
        String cardHolder;
        String cvcCard;
    }
}