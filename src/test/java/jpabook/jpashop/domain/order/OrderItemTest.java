package jpabook.jpashop.domain.order;

import jpabook.jpashop.fixture.domain.item.ItemFixture;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {

    @Test
    void createOrderItemTest() {
        Item album = ItemFixture.createItem("album", 2000, 20);

        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 3);

        assertThat(orderItem.getCount()).isEqualTo(3);
        assertThat(orderItem.getOrderPrice()).isEqualTo(2000);
        assertThat(orderItem.getItem()).isEqualTo(album);
    }

    @Test
    void getTotalPriceTest() {
        Item album = ItemFixture.createItem("album", 2000, 20);

        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 3);

        assertThat(orderItem.getTotalPrice()).isEqualTo(orderItem.getOrderPrice() * orderItem.getCount());
    }
}