package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.delivery.DeliveryStatus;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.ItemRepository;
import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberRepository;
import jpabook.jpashop.web.order.dto.SimpleOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        // 편의를 위해 하나의 주문 당 한가지 상품만 주문하게끔 하였다.
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        return orderRepository.save(Order.createOrder(member, delivery, orderItem));
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        order.cancel();
    }

    public List<SimpleOrderDto> orders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }
}
