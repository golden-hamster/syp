package com.isack.syp.comment.dto.request;

import com.isack.syp.comment.dto.CommentDto;
import lombok.Getter;

@Getter
public class CommentRequest {

    private String content;

    public CommentRequest(String content) {
        this.content = content;
    }

    public CommentDto toDto() {
        return CommentDto.of(content);
    }
}
