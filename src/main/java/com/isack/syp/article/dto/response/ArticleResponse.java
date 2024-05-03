package com.isack.syp.article.dto.response;

import com.isack.syp.article.dto.ArticleDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    private LocalDateTime createdAt;
    private Integer commentCount;
    private String apiId;
    private String thumbnailUrl;

    public ArticleResponse(Long id, String title, String content, String createdBy, LocalDateTime createdAt, Integer commentCount, String apiId, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
        this.apiId = apiId;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getContent(),
                articleDto.getCreatedBy(),
                articleDto.getCreatedAt(),
                articleDto.getCommentCount(),
                articleDto.getPlaylistDto().getApiId(),
                articleDto.getPlaylistDto().getThumbnailUrl()
        );
    }
}
