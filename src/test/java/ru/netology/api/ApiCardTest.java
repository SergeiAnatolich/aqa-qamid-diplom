package ru.netology.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.Api;
import ru.netology.data.DataHelper;
import ru.netology.data.Database;

public class ApiCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldBuyTourOnCardApi() {
        Api.successfullyBuyOnCard(DataHelper.getValidRegisteredApprovedCard());
        Database.checkSuccessfullyBuyTourOnCard();
    }

    @Test
    public void shouldNotBuyTourOnCardApi() {
        Api.declinedBuyOnCard(DataHelper.getValidRegisteredDeclinedCard());
        Database.checkDeclinedBuyTourOnCard();
    }

    @Test
    public void shouldBuyTourOnCreditApi() {
        Api.successfullyBuyOnCredit(DataHelper.getValidRegisteredApprovedCard());
        Database.checkSuccessfullyBuyTourOnCredit();
    }

    @Test
    public void shouldNotBuyTourOnCreditApi() {
        Api.declinedBuyOnCredit(DataHelper.getValidRegisteredDeclinedCard());
        Database.checkDeclinedBuyTourOnCredit();
    }
}
