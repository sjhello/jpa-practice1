package jpabook.jpashop.web.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberResponse {

    private Long id;
    private String username;
}
