package com.isack.syp.comment.dto.response;

import com.isack.syp.comment.dto.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(CommentDto commentDto) {
        return new CommentResponse(
                commentDto.getId(),
                commentDto.getContent(),
                commentDto.getCreatedBy(),
                commentDto.getCreatedAt()
        );
    }
}
