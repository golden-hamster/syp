package com.isack.syp.article.dto.response;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.item.ItemDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private List<ItemDto> itemDtoList;
    private String createdBy;
    private String createdAt;
    private Integer commentCount;
    private Integer likesCount;
    private Boolean isLiked;

    public static ArticleResponse from(ArticleDto articleDto, boolean isLiked) {
        return new ArticleResponse(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getContent(),
                articleDto.getItemDtoList(),
                articleDto.getCreatedBy(),
                articleDto.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                articleDto.getCommentCount(),
                articleDto.getLikesCount(),
                isLiked
        );
    }
}
