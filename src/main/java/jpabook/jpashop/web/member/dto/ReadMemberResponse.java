package jpabook.jpashop.web.member.dto;

import lombok.Getter;

@Getter
public class ReadMemberResponse {

    private Long id;
    private String username;

    public ReadMemberResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
