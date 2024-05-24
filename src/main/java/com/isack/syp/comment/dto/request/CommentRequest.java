package com.isack.syp.comment.dto.request;

import com.isack.syp.comment.dto.CommentDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    private String content;

    public CommentDto toDto() {
        return CommentDto.of(content);
    }
}
