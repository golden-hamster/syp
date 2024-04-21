package com.isack.syp.comment.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentsResponse {
    private List<CommentResponse> comments;

    public CommentsResponse(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public static CommentsResponse from(List<CommentResponse> comments) {
        return new CommentsResponse(comments);
    }
}
