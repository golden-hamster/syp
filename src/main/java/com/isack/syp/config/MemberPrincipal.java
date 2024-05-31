package com.isack.syp.config;

import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;

public class MemberPrincipal extends MemberDto {

    private final Long memberId;

    public MemberPrincipal(Member member) {
        super(member.getId(), member.getUsername(), member.getNickname(), member.getPassword());
        this.memberId = member.getId();
    }

    public Long getMemberId() {
        return memberId;
    }
}
