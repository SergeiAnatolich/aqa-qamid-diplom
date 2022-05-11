package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.Page;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class SendForm {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openBrowser() {
        open("http://localhost:8080/");
        Page.buttonBuy.click();
    }

    @Test
    public void shouldSuccessfullyBuyTourOnRegisteredCard() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldSuccessfullyBuyTourOnCreditWithRegisteredCard() {
        Page.buttonBuyInCredit.click();
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCardForCredit().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithAllFieldsEmpty() {
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCreditWithAllFieldsEmpty() {
        Page.buttonBuyInCredit.click();
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotBuyTourOnCardWithUnregisteredCard() {
        Page.fieldCardNumber.setValue(DataHelper.getValidUnregisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidUnregisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidUnregisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidUnregisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidUnregisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleError.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleError.shouldBe(visible).shouldHave(text("Ошибка"));
        Page.notificationContentError.shouldBe(visible).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    public void shouldNotBuyTourOnCreditWithUnregisteredCard() {
        Page.buttonBuyInCredit.click();
        Page.fieldCardNumber.setValue(DataHelper.getValidUnregisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidUnregisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidUnregisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidUnregisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidUnregisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleError.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleError.shouldBe(visible).shouldHave(text("Ошибка"));
        Page.notificationContentError.shouldBe(visible).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    public void shouldNotBuyTourOnCardWithInvalidAllField() {
        Page.fieldCardNumber.setValue(DataHelper.getInvalidCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getInvalidCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getInvalidCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getInvalidCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getInvalidCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Неверный формат"));
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotBuyTourWithInvalidCard() {
        Page.fieldCardNumber.setValue(DataHelper.getInvalidCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotBuyTourWithMonth00() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("00");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldNotBuyTourWithMonthMore12() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getInvalidCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldNotBuyTourWithYearLessCurrent() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("20");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    @Test
    public void shouldNotBuyTourWithYearMoreCurrentBy6() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("28");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldNotBuyTourWithInvalidCardHolder() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getInvalidCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotBuyTourWithInvalidCvC() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getInvalidCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithFieldCardEmpty() {
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithFieldMonthEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithFieldYearEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithFieldCardHolderEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotSendFormBuyTourOnCardWithFieldCvCEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.buttonNext.click();
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }
}