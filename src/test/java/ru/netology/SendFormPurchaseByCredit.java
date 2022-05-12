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

public class SendFormPurchaseByCredit {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openBrowserAndClickButtonBuyInCredit() {
        open("http://localhost:8080/");
        Page.buttonBuyInCredit.click();
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
    public void shouldNotSendFormWithAllFieldsEmpty() {
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
    public void shouldNotSendFormWithInvalidAllField() {
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
    public void shouldNotSendFormWithInvalidCard() {
        Page.fieldCardNumber.setValue(DataHelper.getInvalidCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWith1FigureFieldCard() {
        Page.fieldCardNumber.setValue("1");
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormTourWith15FigureFieldCard() {
        Page.fieldCardNumber.setValue("012345678912345");
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithOneFigureFieldMonth() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("2");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithMonth00() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("00");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldBuyTourWithMonth01() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("01");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithMonth02() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("02");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithMonth11() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("11");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithMonth12() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("12");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldNotSendFormWithMonth13() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue("13");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldNotSendFormWithYearLessCurrentBy1() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("21");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    @Test
    public void shouldBuyTourWithYearCurrent() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("23");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy1() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("23");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy4() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("26");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy5() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("27");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.notificationTitleOk.should(appear, Duration.ofSeconds(15));
        Page.notificationTitleOk.shouldBe(visible).shouldHave(text("Успешно"));
        Page.notificationContentOk.shouldBe(visible).shouldHave(text("Операция одобрена Банком."));
    }

    @Test
    public void shouldNotSendFormWithYearMoreCurrentBy6() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue("28");
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    @Test
    public void shouldNotSendFormWithInvalidCardHolder() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getInvalidCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithOneFigureFieldCvC() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue("5");
        Page.buttonNext.click();
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithTwoFigureFieldCvC() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue("58");
        Page.buttonNext.click();
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithFieldCardEmpty() {
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithFieldMonthEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormWithFieldYearEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldYear.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    @Test
    public void shouldNotSendFormBuyTourWithFieldCardHolderEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.invalidFormatFieldCardHolder.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNotSendFormWithFieldCvCEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCard().getCardHolder());
        Page.buttonNext.click();
        Page.invalidFormatFieldCvC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }
}
