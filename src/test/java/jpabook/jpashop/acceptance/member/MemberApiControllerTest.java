package jpabook.jpashop.acceptance.member;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jpabook.jpashop.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원 인수 테스트")
class MemberApiControllerTest extends AcceptanceTest {

    private static final String API_PREFIX = "/api/v1/";

    @DisplayName("회원 생성 V1")
    @Test
    void createMemberV1() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "sjhello");

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .body(params).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(API_PREFIX + "members")
                .then().log().all().extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}