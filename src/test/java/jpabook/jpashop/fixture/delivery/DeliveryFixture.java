package jpabook.jpashop.fixture.delivery;

import jpabook.jpashop.delivery.Delivery;
import jpabook.jpashop.delivery.DeliveryStatus;
import jpabook.jpashop.member.Member;

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
