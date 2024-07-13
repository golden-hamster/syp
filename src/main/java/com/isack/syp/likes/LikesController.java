package com.isack.syp.likes;

import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/likes")
@RestController
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{articleId}")
    public ResponseEntity<Void> likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        likesService.likeArticle(articleId, memberDto);
        return ResponseEntity.noContent().build();
    }
}
