package jpabook.jpashop.web.member;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.domain.member.MemberService;
import jpabook.jpashop.web.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 엔티티를 직접 받는 경우
     * 문제점
     *  - 엔티티에 프레젠테이션 계층을 위한 로직이 추가됨
     *   - validation 등
     *  - 엔티티가 변경되면 API 스펙이 변경됨
     *  - 엔티티에 대한 다양한 API가 만들어 지는데, 한 엔티티에 각각의 API를 만족시키기 위한 요구사항 모두를 담기는 어렵다.
     * 결론
     *  - API 스펙에 맞추어 별도의 DTO를 생성한다.
     *
     * */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    /**
     * DTO를 받는 경우
     * - 엔티티와 DTO를 분리
     * - API 스펙이 바뀌어도 엔티티가 바뀌지 않음 그 반대의 경우도 마찬가지
     * */
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest memberRequest) {
        Member member = new Member();
        member.setUsername(memberRequest.getUsername());
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @PatchMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest updateMemberRequest) {
        memberService.update(id, updateMemberRequest.getUsername());
        Member findMember = memberService.findById(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getUsername());
    }

    @GetMapping("/api/v2/members/{id}")
    public ReadMemberResponse updateMemberV2(@PathVariable Long id) {
        Member member = memberService.findById(id);
        return new ReadMemberResponse(member.getId(), member.getUsername());
    }
}
