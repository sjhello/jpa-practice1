package jpabook.jpashop.web.order.dto;

import jpabook.jpashop.domain.order.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private String itemName;
    private Integer orderPrice;
    private Integer count;

    public OrderItemDto(OrderItem orderItem) {
        this.itemName = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }
}
