package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class Api {

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void successfullyBuyOnCard(DataHelper.Card card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);
    }

    public static void declinedBuyOnCard(DataHelper.Card card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(500);
    }

    public static void successfullyBuyOnCredit(DataHelper.Card card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);
    }

    public static void declinedBuyOnCredit(DataHelper.Card card) {
        given()
                .spec(requestSpec)
                .body(card)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(500);
    }
}
