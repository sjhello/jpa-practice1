package jpabook.jpashop.acceptance.order;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class OrderCollectionSteps {

    private final static String FIND_ORDERS_V2_PATH = "/api/v2/orders";
    private final static String FIND_ORDERS_V3_PATH = "/api/v3/orders";

    public static ExtractableResponse<Response> 컬렉션_API_주문을_조회한다_V2() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS_V2_PATH)
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 컬렉션_API_주문을_조회한다_V3() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(FIND_ORDERS_V3_PATH)
                .then().log().all().extract();
        return response;
    }
}
