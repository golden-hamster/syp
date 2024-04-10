package com.isack.syp.member.dto.response;

import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class MemberResponse {
    private String loginId;
    private String nickname;

    public MemberResponse(String loginId, String nickname) {
        this.loginId = loginId;
        this.nickname = nickname;
    }

    public static MemberResponse from(MemberDto memberDto) {
        return new MemberResponse(memberDto.getLoginId(), memberDto.getNickname());
    }
}
