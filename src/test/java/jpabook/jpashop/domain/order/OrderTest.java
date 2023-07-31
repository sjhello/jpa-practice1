package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.delivery.DeliveryStatus;
import jpabook.jpashop.fixture.domain.delivery.DeliveryFixture;
import jpabook.jpashop.fixture.domain.item.ItemFixture;
import jpabook.jpashop.fixture.domain.member.MemberFixture;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.order.exception.AlreadyShippedException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderTest {

    @Test
    void createOrder() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "seoul street", "seoul zipcode");
        Delivery delivery = DeliveryFixture.createDelivery(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 10);

        // when
        Order createOrder = Order.createOrder(member, delivery, orderItem);

        // then
        assertAll(() -> {
            assertThat(createOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
            assertThat(createOrder.getDelivery()).isEqualTo(delivery);
            assertThat(createOrder.getMember()).isEqualTo(member);
            assertThat(createOrder.getOrderItems()).contains(orderItem);
        });
    }

    @Test
    void cancelTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "seoul street", "seoul zipcode");
        Delivery delivery = DeliveryFixture.createDelivery(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 10);

        // when
        Order order = Order.createOrder(member, delivery, orderItem);
        order.cancel();

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    void cancelFailTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "seoul street", "seoul zipcode");
        Delivery delivery = DeliveryFixture.createDelivery(member, DeliveryStatus.COMP);
        Item album = ItemFixture.createItem("album", 2000, 20);
        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 10);

        // when
        Order order = Order.createOrder(member, delivery, orderItem);

        // then
        assertThatThrownBy(order::cancel)
                .isInstanceOf(AlreadyShippedException.class)
                .hasMessage("이미 배송완료된 물품은 취소 할 수 없습니다");
    }

    @Test
    void getTotalPriceTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "seoul street", "seoul zipcode");
        Delivery delivery = DeliveryFixture.createDelivery(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        OrderItem orderItem = OrderItem.createOrderItem(album, 2000, 10);

        Item album2 = ItemFixture.createItem("album2", 3000, 10);
        OrderItem orderItem2 = OrderItem.createOrderItem(album2, 2000, 5);

        // when
        Order order = Order.createOrder(member, delivery, orderItem, orderItem2);
        int totalPrice = order.getTotalPrice();

        // then
        int albumPrice = orderItem.getOrderPrice() * orderItem.getCount();
        int albumPrice2 = orderItem2.getOrderPrice() * orderItem2.getCount();
        assertThat(totalPrice).isEqualTo(albumPrice + albumPrice2);
    }

}