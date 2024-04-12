package com.isack.syp.member.dto.request;

import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class MemberJoinRequest {
    private String username;
    private String nickname;
    private String password;

    public MemberJoinRequest(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    public MemberDto toDto() {
        return MemberDto.of(username, nickname, password);
    }
}
