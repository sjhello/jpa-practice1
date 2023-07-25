package jpabook.jpashop.order;

import jpabook.jpashop.delivery.DeliveryStatus;
import jpabook.jpashop.fixture.item.ItemFixture;
import jpabook.jpashop.fixture.member.MemberFixture;
import jpabook.jpashop.item.Item;
import jpabook.jpashop.item.ItemRepository;
import jpabook.jpashop.item.exception.NotEnoughStockException;
import jpabook.jpashop.member.Member;
import jpabook.jpashop.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderService orderService;

    @Test
    void orderTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "street", "zipcode");
        memberRepository.save(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        itemRepository.save(album);

        // when
        Long orderId = orderService.order(member.getId(), album.getId(), 10);

        // then
        Order findOrder = orderRepository.findById(orderId);
        assertThat(findOrder.getMember()).isEqualTo(member);
        assertThat(findOrder.getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(album.getStockQuantity()).isEqualTo(10);
    }

    @Test
    void cancelOrderTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "street", "zipcode");
        memberRepository.save(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        itemRepository.save(album);

        // when
        Long orderId = orderService.order(member.getId(), album.getId(), 10);
        orderService.cancelOrder(orderId);

        // then
        Order findOrder = orderRepository.findById(orderId);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(album.getStockQuantity()).isEqualTo(20);
    }

    @Test
    void orderNotEnoughStockExceptionTest() {
        // given
        Member member = MemberFixture.createMember("sjhello", "seoul", "street", "zipcode");
        memberRepository.save(member);
        Item album = ItemFixture.createItem("album", 2000, 20);
        itemRepository.save(album);

        // when & then
        assertThatThrownBy(() -> orderService.order(member.getId(), album.getId(), 21))
                .isInstanceOf(NotEnoughStockException.class);

    }
}