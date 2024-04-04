package com.isack.syp.member.dto;

import com.isack.syp.member.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberDto implements UserDetails {

    private Long id;
    private String loginId;
    private String nickname;
    private String password;

    public MemberDto(Long id, String loginId, String nickname, String password) {
        this.id = id;
        this.loginId = loginId;
        this.nickname = nickname;
        this.password = password;
    }

    public static MemberDto of(String loginId, String nickname, String password) {
        return new MemberDto(null, loginId, nickname, password);
    }

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getLoginId(),
                member.getNickname(),
                member.getPassword()
        );
    }

    public Member toEntity() {
        return Member.of(loginId, nickname, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return loginId;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}
