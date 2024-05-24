package com.isack.syp.article.dto.response;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.dto.PlaylistItemDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private List<PlaylistItemDto> playlistItemDtoList;
    private String createdBy;
    private LocalDateTime createdAt;
    private Integer commentCount;

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getContent(),
                articleDto.getPlaylistItemDtoList(),
                articleDto.getCreatedBy(),
                articleDto.getCreatedAt(),
                articleDto.getCommentCount()
        );
    }
}
