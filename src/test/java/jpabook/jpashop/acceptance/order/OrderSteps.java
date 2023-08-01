package jpabook.jpashop.acceptance.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderSteps {

    private static final String FIND_ORDERS = "/api/v2/simple-orders";

    public static ExtractableResponse<Response> 모든_주문을_조회한다_V2() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS)
                .then().log().all().extract();
        return response;
    }
}
