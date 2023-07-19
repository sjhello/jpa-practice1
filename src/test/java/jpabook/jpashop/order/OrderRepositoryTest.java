package jpabook.jpashop.order;

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
    public void createOrder() {
        // given
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);

        Order order2 = new Order();
        order2.setOrderDate(LocalDateTime.now());
        order2.setStatus(OrderStatus.CANCEL);

        // when
        Long orderId = orderRepository.save(order);
        Long orderId2 = orderRepository.save(order2);

        // then
        assertThat(order.getId()).isEqualTo(orderId);
    }

}