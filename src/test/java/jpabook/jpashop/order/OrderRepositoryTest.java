package jpabook.jpashop.order;

import jpabook.jpashop.delivery.Delivery;
import jpabook.jpashop.delivery.DeliveryStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void orderTest() {
        // given
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);

        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);

        order.setDelivery(delivery);

        // when
        Long orderId = orderRepository.save(order);
        Order findOrder = orderRepository.findById(1L);
        orderRepository.deleteById(findOrder.getId());

        // then
        assertThat(order.getId()).isEqualTo(orderId);
    }
}