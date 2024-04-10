package com.isack.syp.member.controller;

import com.isack.syp.member.dto.request.MemberJoinRequest;
import com.isack.syp.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        Long memberId = memberService.createMember(memberJoinRequest.toDto());
        return ResponseEntity.created(URI.create("/api/members/" + memberId)).build();
    }
}
