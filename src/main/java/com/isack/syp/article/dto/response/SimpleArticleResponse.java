package com.isack.syp.article.dto.response;

import com.isack.syp.article.dto.ArticleDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleArticleResponse {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private String createdBy;
    private LocalDateTime createdAt;
    private Integer commentCount;

    public static SimpleArticleResponse from(ArticleDto articleDto) {
        return new SimpleArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getThumbnailUrl(),
                articleDto.getCreatedBy(),
                articleDto.getCreatedAt(),
                articleDto.getCommentCount()
        );
    }
}
