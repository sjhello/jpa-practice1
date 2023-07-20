package jpabook.jpashop.unit;

import jpabook.jpashop.delivery.Delivery;
import jpabook.jpashop.delivery.DeliveryStatus;
import jpabook.jpashop.member.Address;
import jpabook.jpashop.member.Member;
import jpabook.jpashop.order.Order;
import jpabook.jpashop.order.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderTest {

    @Test
    void createOrder() {
        // given
        Member member = new Member();
        member.setUsername("sjhello");
        member.setAddress(new Address("seoul", "seoul street", "seoul zipcode"));

        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        // when
        Order createOrder = Order.createOrder(member, delivery);

        // then
        assertAll(() -> {
            assertThat(createOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
            assertThat(createOrder.getDelivery()).isEqualTo(delivery);
            assertThat(createOrder.getMember()).isEqualTo(member);
        });
    }

}