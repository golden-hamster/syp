package com.isack.syp.member.dto.response;

import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String username;
    private String nickname;

    public MemberResponse(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public static MemberResponse from(MemberDto memberDto) {
        return new MemberResponse(memberDto.getUsername(), memberDto.getNickname());
    }
}
