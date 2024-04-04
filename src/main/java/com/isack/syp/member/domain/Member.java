package com.isack.syp.member.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Member {

    @GeneratedValue
    @Id
    private Long id;

    private String loginId;

    private String nickname;

    private String password;

    protected Member() {}

    private Member(String loginId, String nickname, String password) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }

    public static Member of(String loginId, String nickname, String password) {
        return new Member(loginId, nickname, password);
    }
}
