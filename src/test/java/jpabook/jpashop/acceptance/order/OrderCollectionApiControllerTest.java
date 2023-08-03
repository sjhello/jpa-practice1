package jpabook.jpashop.acceptance.order;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jpabook.jpashop.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static jpabook.jpashop.acceptance.order.OrderCollectionSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문 인수 테스트 - 컬렉션 성능 최적화")
class OrderCollectionApiControllerTest extends AcceptanceTest {


    @DisplayName("주문 조회 V2 - fetch join 미적용")
    @Test
    void testOrdersV2() {
        ExtractableResponse<Response> response = 컬렉션_API_주문을_조회한다_V2();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("주문 조회 V3 - fetch join 최적화")
    @Test
    void testOrdersV3() {
        ExtractableResponse<Response> response = 컬렉션_API_주문을_조회한다_V3();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("주문 조회 V3.1 - fetch join 최적화, 페이징 적용")
    @Test
    void testOrdersPage() {
        ExtractableResponse<Response> response = 컬렉션_API_주문을_조회한다_V3_1();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}