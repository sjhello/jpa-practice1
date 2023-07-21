package jpabook.jpashop.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    void memberJoinSuccessTest() {
        // given
        Member member = new Member();
        member.setUsername("sjhello");

        // when
        Long memberId = memberService.join(member);

        // then
        assertThat(member).isEqualTo(memberRepository.findById(memberId));
    }

    @Test
    void memberJoinFailTest() {
        // given
        Member successMember = new Member();
        successMember.setUsername("sjhello");

        Member failMember = new Member();
        failMember.setUsername("sjhello");

        // when
        Long memberId = memberService.join(successMember);

        // then
        assertThatThrownBy(() -> memberService.join(failMember))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 회원 입니다");
    }
}