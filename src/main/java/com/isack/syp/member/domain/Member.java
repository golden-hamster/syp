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

    private String username;

    private String nickname;

    private String password;

    protected Member() {}

    private Member(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }

    public static Member of(String username, String nickname, String password) {
        return new Member(username, nickname, password);
    }
}
