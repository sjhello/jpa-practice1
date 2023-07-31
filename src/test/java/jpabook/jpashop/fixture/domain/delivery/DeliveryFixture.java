package jpabook.jpashop.fixture.domain.delivery;

import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.delivery.DeliveryStatus;
import jpabook.jpashop.domain.member.Member;

public class DeliveryFixture {

    public static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setAddress(member.getAddress());

        return delivery;
    }

    public static Delivery createDelivery(Member member, DeliveryStatus deliveryStatus) {
        Delivery delivery = new Delivery();
        delivery.setStatus(deliveryStatus);
        delivery.setAddress(member.getAddress());

        return delivery;
    }
}
