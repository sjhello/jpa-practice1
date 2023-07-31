package jpabook.jpashop.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicatMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicatMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getUsername());
        if (!members.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원 입니다");
        }
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
