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

import static jpabook.jpashop.acceptance.member.MemberSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원 인수 테스트")
class MemberApiControllerTest extends AcceptanceTest {

    @DisplayName("회원 생성 V1")
    @Test
    void createMemberV1() {
        ExtractableResponse<Response> response = 회원을_생성한다_V1();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("회원 생성 V2")
    @Test
    void createMemberV2() {
        ExtractableResponse<Response> response = 회원을_생성한다_V2();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 회원을 생성하고
     * When 회원의 이름을 수정하면
     * Then 수정된 회원의 정보를 알 수 있다.
     * */
    @DisplayName("회원 수정 V2")
    @Test
    void updateMemberV2() {
        // given
        ExtractableResponse<Response> createMemberResponse = 회원을_생성한다_V2();

        // when
        ExtractableResponse<Response> updateMemberResponse = 회원이름을_수정한다_V2(createMemberResponse.jsonPath().getLong("id"));

        // then
        assertThat(updateMemberResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(updateMemberResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(updateMemberResponse.jsonPath().getLong("id")).isEqualTo(createMemberResponse.jsonPath().getLong("id"));
        assertThat(updateMemberResponse.jsonPath().getString("username")).isEqualTo("sjhello2222");
    }

    /**
     * Given 회원을 생성하고
     * When 생성한 회원을 조회하면
     * Then 생성한 회원의 정보를 응답 받을 수 있다.
     * */
    @DisplayName("회원 조회 V2")
    @Test
    void readMemberV2() {
        // given
        ExtractableResponse<Response> createMemberResponse = 회원을_생성한다_V2();

        // when
        ExtractableResponse<Response> readMemberResponse = 회원을_조회한다_V2(createMemberResponse.jsonPath().getLong("id"));

        // then
        assertThat(createMemberResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(readMemberResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(readMemberResponse.jsonPath().getString("username")).isEqualTo("sjhello");
    }
}