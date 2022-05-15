package ru.netology.buyTour;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;

public class PurchaseByCreditTest {

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
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCardForCredit().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldNotBuyTourOnCardWithUnregisteredCard() {
        Page.fieldCardNumber.setValue(DataHelper.getValidUnregisteredCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidUnregisteredCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidUnregisteredCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidUnregisteredCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidUnregisteredCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkNotificationTitleError();
    }

    @Test
    public void shouldBuyTourWithMonth01() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue("01");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithMonth02() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue("02");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithMonth11() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue("11");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithMonth12() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue("12");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredCardForCredit().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithMonthCurrentAndYearCurrent() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getMonth(0));
        Page.fieldYear.setValue(DataHelper.getYear(0));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy1() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCardForCredit().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getYear(1));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy4() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredCardForCredit().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getYear(4));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

    @Test
    public void shouldBuyTourWithMonthCurrentAndYearMoreCurrentBy5() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredCardForCredit().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getMonth(0));
        Page.fieldYear.setValue(DataHelper.getYear(5));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredCardForCredit().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredCardForCredit().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
    }

}