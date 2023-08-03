package jpabook.jpashop.web.order.dto;

import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderItem;
import jpabook.jpashop.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문시간
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;
    private Integer orderPrice;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getUsername();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
        this.orderPrice = order.getOrderItems().stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
