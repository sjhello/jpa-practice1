package jpabook.jpashop.acceptance.order;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jpabook.jpashop.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static jpabook.jpashop.acceptance.order.OrderSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문 인수 테스트")
class OrderSimpleApiControllerTest extends AcceptanceTest {

    @DisplayName("주문 조회 V2")
    @Test
    void findOrdersV2() {
        ExtractableResponse<Response> response = 모든_주문을_조회한다_V2();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("orderId")).hasSize(3);
    }

    @DisplayName("주문 조회 V3")
    @Test
    void findOrdersV3() {
        ExtractableResponse<Response> response = 모든_주문을_조회한다_V3();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("orderId")).hasSize(3);
    }

    @DisplayName("주문 조회 V4")
    @Test
    void findOrdersV4() {
        ExtractableResponse<Response> response = 모든_주문을_조회한다_V4();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("orderId")).hasSize(3);
    }

}