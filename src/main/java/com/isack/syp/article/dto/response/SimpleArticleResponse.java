package com.isack.syp.article.dto.response;

import com.isack.syp.article.dto.ArticleDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleArticleResponse {
    private Long id;
    private String title;
    private String thumbnailUrl;
    private String createdBy;
    private String createdAt;
    private Integer commentCount;
    private Integer likesCount;

    public static SimpleArticleResponse from(ArticleDto articleDto) {
        return new SimpleArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getThumbnailUrl(),
                articleDto.getCreatedBy(),
                articleDto.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                articleDto.getCommentCount(),
                articleDto.getLikesCount()
        );
    }
}
