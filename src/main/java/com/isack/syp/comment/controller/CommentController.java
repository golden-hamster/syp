package com.isack.syp.comment.controller;

import com.isack.syp.comment.dto.request.CommentRequest;
import com.isack.syp.comment.service.CommentService;
import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private CommentService commentService;

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Void> saveComment(@PathVariable Long articleId,
                                            @RequestBody CommentRequest commentRequest,
                                            @AuthenticationPrincipal MemberDto memberDto) {
        Long commentId = commentService.saveComment(articleId, memberDto, commentRequest.toDto());
        return ResponseEntity.created(URI.create("/api/comments/" + commentId)).build();
    }
}
