package com.isack.syp.member.service;

import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDto findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname).map(MemberDto::from).orElseThrow(IllegalAccessError::new);
    }


    @Transactional
    public Long createMember(MemberDto memberDto) {
        return memberRepository.save(Member.of(memberDto.getUsername(), memberDto.getNickname(), passwordEncoder.encode(memberDto.getPassword()))).getId();
    }

}
