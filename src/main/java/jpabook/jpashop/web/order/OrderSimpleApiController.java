package jpabook.jpashop.web.order;

import jpabook.jpashop.domain.order.OrderService;
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
}
