package com.isack.syp.member.dto.request;

import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class MemberJoinRequest {
    private String longId;
    private String nickname;
    private String password;

    public MemberJoinRequest(String longId, String nickname, String password) {
        this.longId = longId;
        this.nickname = nickname;
        this.password = password;
    }

    public MemberDto toDto() {
        return MemberDto.of(longId, nickname, password);
    }
}
