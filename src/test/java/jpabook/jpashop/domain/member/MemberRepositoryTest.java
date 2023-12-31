package jpabook.jpashop.domain.member;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() {
        Member newMember = new Member();
        newMember.setUsername("newMember");

        Long memberId = memberRepository.save(newMember);

        Member findMember = memberRepository.findById(memberId);

        assertThat(memberId).isEqualTo(findMember.getId());
    }
}