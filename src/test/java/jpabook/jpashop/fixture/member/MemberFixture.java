package jpabook.jpashop.fixture.member;

import jpabook.jpashop.member.Address;
import jpabook.jpashop.member.Member;

public class MemberFixture {

    public static Member createMemberWithNameAndAddress() {
        Member member = new Member();
        member.setUsername("sjhello");
        member.setAddress(new Address("seoul", "seoul street", "seoul zipcode"));

        return member;
    }
}
