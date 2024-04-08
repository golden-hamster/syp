package com.isack.syp.comment.dto.response;

import com.isack.syp.comment.dto.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long id;
    private String title;
    private String createBy;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String title, String createBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createBy = createBy;
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
