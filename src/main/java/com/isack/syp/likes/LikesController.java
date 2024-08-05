package com.isack.syp.likes;

import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/{articleId}")
@RestController
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/likes")
    public ResponseEntity<LikesResponse> likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        Integer likesCount = likesService.likeArticle(articleId, memberDto);
        return ResponseEntity.ok(LikesResponse.of(likesCount));
    }

    @DeleteMapping("/likes")
    public ResponseEntity<LikesResponse> unlikeArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        Integer likesCount = likesService.unlikeArticle(articleId, memberDto);
        return ResponseEntity.ok(LikesResponse.of(likesCount));
    }
}
