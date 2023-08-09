package jpabook.jpashop.web.order;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderQueryRepository;
import jpabook.jpashop.domain.order.OrderRepository;
import jpabook.jpashop.web.order.dto.OrderDto;
import jpabook.jpashop.web.order.dto.OrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderCollectionApiController {

    /**
     * 일대다 OneToMany 컬렉션 조회 시 성능 최적화
     */

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    /**
     * V1 엔티티 직접 노출
     * */


    /**
     * V2 엔티티를 DTO로 변환
     * - 1 + N 문제 발생(N + 1 문제)
     * - 트랜잭션 안에서 지연 로딩
     */
    @RequestMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }


    /**
     * V3 엔티티를 DTO로 변환
     * - fetch join 최적화
     * - 페이징 X -> 페이징 사용하려면 batch size 옵션 사용해야함
     */
    @RequestMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    /**
     * V3.1 엔티티를 DTO로 변환 - 페이징과 한계 돌파
     * - fetch join 최적화
     * - 페이징 O -> batch size 옵션 사용
     */
    @RequestMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_page(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                       @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);
        return orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
    
    /**
     * V4 JPA에서 직접 DTO 조회
     * */
    @RequestMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }
}
