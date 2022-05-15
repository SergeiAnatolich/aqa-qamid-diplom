package ru.netology.data;

import com.codeborne.selenide.SelenideElement;
import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
public class Page {
    public SelenideElement buttonBuy = $(withText("Купить"));
    public SelenideElement buttonBuyInCredit = $(withText("Купить в кредит"));
    public SelenideElement fieldCardNumber = $("[placeholder='0000 0000 0000 0000']");
    public SelenideElement invalidFormatFieldCardNumber = $x("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]");
    public SelenideElement fieldMonth = $("[placeholder='08']");
    public SelenideElement invalidFormatFieldMonth = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    public SelenideElement fieldYear = $("[placeholder='22']");
    public SelenideElement invalidFormatFieldYear = $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    public SelenideElement fieldCardHolder = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    public SelenideElement invalidFormatFieldCardHolder = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");
    public SelenideElement fieldCvC = $("[placeholder='999']");
    public SelenideElement invalidFormatFieldCvC = $x("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");
    public SelenideElement buttonNext = $(withText("Продолжить"));
    public SelenideElement notificationTitleOk = $(".notification_status_ok .notification__title");
    public SelenideElement notificationContentOk = $(".notification_status_ok .notification__content");
    public SelenideElement notificationTitleError = $(".notification_status_error .notification__title");
    public SelenideElement notificationContentError =  $(".notification_status_error .notification__content");
    public SelenideElement buttonSend = $(withText("Отправляем запрос в Банк..."));

    public void checkFieldCardNumber() {
        invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void checkFieldMonthFormat() {
        invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void checkFieldMonthInvalidPeriod() {
        invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void checkFieldMonthExpiredPeriod() {
        invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void checkFieldYear() {
        invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void checkFieldCardHolderFormat() {
        invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void checkFieldCardHolderEmpty() {
        invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void checkFieldCvC() {
        invalidFormatFieldCvC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void checkButtonSendVisible() {
        buttonSend.shouldBe(visible);
    }

    public void checkButtonSendNotVisible() {
        buttonSend.shouldNotBe(visible, Duration.ofSeconds(1));
    }

    public void checkNotificationTitleOk() {
        notificationTitleOk.should(appear, Duration.ofSeconds(15));
        notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    public void checkNotificationTitleError() {
        notificationTitleError.should(appear, Duration.ofSeconds(15));
        notificationTitleError.shouldBe(visible).shouldHave(text("Ошибка"));
        notificationContentError.shouldBe(visible).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }
}