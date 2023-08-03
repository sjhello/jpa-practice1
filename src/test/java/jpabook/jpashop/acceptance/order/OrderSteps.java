package jpabook.jpashop.acceptance.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderSteps {

    private static final String FIND_ORDERS = "/api/v2/simple-orders";
    private static final String FIND_ORDERS_FETCH = "/api/v3/simple-orders";
    private static final String FIND_ORDERS_JPA_TO_DTO = "/api/v4/simple-orders";

    public static ExtractableResponse<Response> 모든_주문을_조회한다_V2() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS)
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 모든_주문을_조회한다_V3() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS_FETCH)
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 모든_주문을_조회한다_V4() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS_JPA_TO_DTO)
                .then().log().all().extract();
        return response;
    }
}