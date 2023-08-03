package jpabook.jpashop.web.order;

import jpabook.jpashop.domain.order.OrderService;
import jpabook.jpashop.web.order.dto.OrderSimpleQueryDto;
import jpabook.jpashop.web.order.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderService orderService;

    /**
     * V1
     * XtoOne 연관 관계 시 문제
     *  - 엔티티를 API에 노출시키지 않는다.
     *  - 양방향 이라면 json으로 변환 할 때 무한 로딩에 빠짐
     *
     * */

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderService.orders();
    }

    /**
     * V3 엔티티를 DTO로 변환
     * Fetch Join으로 지연로딩 N + 1 문제 해결
     * */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        return orderService.ordersFetch();
    }

    /**
     * V4 JPA에서 DTO로 바로 조회
     * */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderService.ordersFindQuery();
    }
}
