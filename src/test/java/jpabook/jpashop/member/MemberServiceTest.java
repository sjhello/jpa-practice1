package jpabook.jpashop.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Test
    void findMembersTest() {
        // given
        Member member1 = new Member();
        member1.setUsername("sjhello");
        Member member2 = new Member();
        member2.setUsername("sjhello");

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members).hasSize(2);
        assertThat(members).containsExactly(member1, member2);
    }

    @Test
    void findById() {
        Member member1 = new Member();
        member1.setUsername("sjhello");

        memberRepository.save(member1);
        Member findMember = memberService.findById(member1.getId());

        assertThat(findMember.getId()).isEqualTo(member1.getId());
    }
}
