package jpabook.jpashop.web.member.dto;

import lombok.Data;

@Data
public class CreateMemberResponse {

    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
