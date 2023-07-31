package jpabook.jpashop.acceptance.member;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MemberSteps {

    private static final String API_PREFIX = "/api/";

    public static ExtractableResponse<Response> 회원을_생성한다_V1() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "sjhello");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(API_PREFIX + "v1/members")
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 회원을_생성한다_V2() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "sjhello");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(API_PREFIX + "v2/members")
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 회원이름을_수정한다_V2(Long id) {
        Map<String, String> params = new HashMap<>();
        params.put("username", "sjhello2222");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().patch(API_PREFIX + "v2/members/" + id)
                .then().log().all().extract();
        return response;
    }

    public static ExtractableResponse<Response> 회원을_조회한다_V2(Long id) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get(API_PREFIX + "v2/members/" + id)
                .then().log().all().extract();
        return response;
    }
}
