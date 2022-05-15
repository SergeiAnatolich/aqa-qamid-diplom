package ru.netology.notSendForm;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.Page;

import static com.codeborne.selenide.Selenide.open;

public class SendFormPurchaseByCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openBrowserAndClickButtonBuy() {
        open("http://localhost:8080/");
        Page.buttonBuy.click();
    }

    @Test
    public void shouldNotSendFormWithAllFieldsEmpty() {
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
        Page.checkFieldMonthFormat();
        Page.checkFieldYear();
        Page.checkFieldCardHolderEmpty();
        Page.checkFieldCvC();
    }

    @Test
    public void shouldNotSendFormWithInvalidAllField() {
        Page.fieldCardNumber.setValue(DataHelper.getInvalidCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getInvalidCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getInvalidCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getInvalidCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getInvalidCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
        Page.checkFieldMonthFormat();
        Page.checkFieldYear();
        Page.checkFieldCardHolderFormat();
        Page.checkFieldCvC();
    }

    @Test
    public void shouldNotSendFormWithInvalidCard() {
        Page.fieldCardNumber.setValue(DataHelper.getInvalidCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
    }

    @Test
    public void shouldNotSendFormWithOneDigitInFieldCard() {
        Page.fieldCardNumber.setValue(DataHelper.getOneDigit());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
    }

    @Test
    public void shouldNotSendFormTourWith15DigitsInFieldCard() {
        Page.fieldCardNumber.setValue(DataHelper.get15Digits());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
    }

    @Test
    public void shouldNotSendFormWithOneDigitInFieldMonth() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getOneDigit());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthFormat();
    }

    @Test
    public void shouldNotSendFormWithMonth00() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue("00");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthInvalidPeriod();
    }

    @Test
    public void shouldNotSendFormWithMonth13() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue("13");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthInvalidPeriod();
    }

    @Test
    public void shouldNotSendFormWithDateLessCurrentBy1Month() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getMonth(-1));
        Page.fieldYear.setValue(DataHelper.getActualYearWithChangeMonth(-1, 0));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthExpiredPeriod();
    }

    @Test
    public void shouldNotSendFormWithDateMoreCurrentBy1MonthAnd5Years() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getMonth(1));
        Page.fieldYear.setValue(DataHelper.getActualYearWithChangeMonth(1, 5));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthInvalidPeriod();
    }

    @Test
    public void shouldNotSendFormWithYearMoreCurrentBy6() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getYear(6));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthInvalidPeriod();
    }

    @Test
    public void shouldNotSendFormWithInvalidCardHolder() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getInvalidCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardHolderFormat();
    }

    @Test
    public void shouldNotSendFormWithOneDigitInFieldCvC() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getOneDigit());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCvC();
    }

    @Test
    public void shouldNotSendFormWithTwoDigitsInFieldCvC() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getTwoDigits());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCvC();
    }

    @Test
    public void shouldNotSendFormWithFieldCardEmpty() {
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardNumber();
    }

    @Test
    public void shouldNotSendFormWithFieldMonthEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldMonthFormat();
    }

    @Test
    public void shouldNotSendFormWithFieldYearEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldYear();
    }

    @Test
    public void shouldNotSendFormBuyTourWithFieldCardHolderEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvcCard());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCardHolderEmpty();
    }

    @Test
    public void shouldNotSendFormWithFieldCvCEmpty() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumberCard());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonthCard());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYearCard());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getCardHolder());
        Page.buttonNext.click();
        Page.checkButtonSendNotVisible();
        Page.checkFieldCvC();
    }
}