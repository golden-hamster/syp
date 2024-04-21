package com.isack.syp.comment.controller;

import com.isack.syp.comment.dto.request.CommentRequest;
import com.isack.syp.comment.dto.response.CommentResponse;
import com.isack.syp.comment.dto.response.CommentsResponse;
import com.isack.syp.comment.service.CommentService;
import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentsResponse> findComments(@PathVariable Long articleId) {
        List<CommentResponse> comments = commentService.findByArticleId(articleId).stream().map(CommentResponse::from).toList();
        CommentsResponse commentsResponse = CommentsResponse.from(comments);
        return ResponseEntity.ok(commentsResponse);
    }


    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Void> saveComment(@PathVariable Long articleId,
                                            @RequestBody CommentRequest commentRequest,
                                            @AuthenticationPrincipal MemberDto memberDto) {
        Long commentId = commentService.saveComment(articleId, memberDto, commentRequest.toDto());
        return ResponseEntity.created(URI.create("/api/comments/" + commentId)).build();
    }

    @PostMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDto memberDto) {
        commentService.deleteComment(commentId, memberDto);
        return ResponseEntity.noContent().build();
    }
}
