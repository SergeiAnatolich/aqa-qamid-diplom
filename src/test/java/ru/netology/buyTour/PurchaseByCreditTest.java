package ru.netology.buyTour;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.Database;
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
    public void shouldSuccessfullyBuyTourOnApprovedCard() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonth());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldNotBuyTourOnDeclinedCard() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredDeclinedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredDeclinedCard().getMonth());
        Page.fieldYear.setValue(DataHelper.getValidRegisteredDeclinedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredDeclinedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredDeclinedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleError();
        Database.checkDeclinedBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonth01() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue("01");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonth02() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue("02");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonth11() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue("11");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonth12() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue("12");
        Page.fieldYear.setValue(DataHelper.getValidRegisteredApprovedCard().getYear());
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonthCurrentAndYearCurrent() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getMonth(0));
        Page.fieldYear.setValue(DataHelper.getYear(0));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy1() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonth());
        Page.fieldYear.setValue(DataHelper.getYear(1));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithYearMoreCurrentBy4() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getValidRegisteredApprovedCard().getMonth());
        Page.fieldYear.setValue(DataHelper.getYear(4));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldBuyTourWithMonthCurrentAndYearMoreCurrentBy5() {
        Page.fieldCardNumber.setValue(DataHelper.getValidRegisteredApprovedCard().getNumber());
        Page.fieldMonth.setValue(DataHelper.getMonth(0));
        Page.fieldYear.setValue(DataHelper.getYear(5));
        Page.fieldCardHolder.setValue(DataHelper.getValidRegisteredApprovedCard().getHolder());
        Page.fieldCvC.setValue(DataHelper.getValidRegisteredApprovedCard().getCvc());
        Page.buttonNext.click();
        Page.checkButtonSendVisible();
        Page.checkNotificationTitleOk();
        Database.checkSuccessfullyBuyTourOnCredit();
    }
}