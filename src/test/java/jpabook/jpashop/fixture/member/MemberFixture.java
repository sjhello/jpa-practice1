package jpabook.jpashop.fixture.member;

import jpabook.jpashop.member.Address;
import jpabook.jpashop.member.Member;

public class MemberFixture {

    public static Member createMember(String username, String city, String street, String zipCode) {
        Member member = new Member();
        member.setUsername(username);
        member.setAddress(new Address(city, street, zipCode));

        return member;
    }
}
