package com.isack.syp.member.controller;

import com.isack.syp.member.dto.request.LoginRequest;
import com.isack.syp.member.dto.request.MemberJoinRequest;
import com.isack.syp.member.dto.response.MemberResponse;
import com.isack.syp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{nickname}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable String nickname) {
        MemberResponse memberResponse = MemberResponse.from(memberService.findByNickname(nickname));
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        Long memberId = memberService.createMember(memberJoinRequest.toDto());
        return ResponseEntity.created(URI.create("/api/members/" + memberId)).build();
    }

}
