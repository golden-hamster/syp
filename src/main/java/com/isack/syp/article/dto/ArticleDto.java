package com.isack.syp.article.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.item.Item;
import com.isack.syp.item.ItemDto;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleDto {
    private Long id;
    private MemberDto memberDto;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private Integer commentCount;
    private Integer likesCount;
    private String thumbnailUrl;
    private List<ItemDto> itemDtoList;

    public ArticleDto(Long id, MemberDto memberDto, String title, String content, LocalDateTime createdAt, String createdBy, Integer commentsCount, Integer likesCount,String thumbnailUrl,List<ItemDto> itemDtoList) {
        this.id = id;
        this.memberDto = memberDto;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.commentCount = commentsCount;
        this.likesCount = likesCount;
        this.thumbnailUrl = thumbnailUrl;
        this.itemDtoList = itemDtoList;
    }

    public static ArticleDto of(MemberDto memberDto, String title, String content, String thumbnailUrl,List<ItemDto> itemDtoList) {
        return new ArticleDto(null, memberDto, title, content, null, null, null, null, thumbnailUrl, itemDtoList);
    }


    public Article toEntity(Member member) {
        return Article.of(member, title, content, thumbnailUrl);
    }

    public static ArticleDto from(Article article, List<Item> items) {
        List<ItemDto> itemDtoList = items.stream()
                .map(ItemDto::from)
                .collect(Collectors.toList());

        return new ArticleDto(
                article.getId(),
                MemberDto.from(article.getMember()),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getCommentCount(),
                article.getLikesCount(),
                article.getThumbnailUrl(),
                itemDtoList
        );
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId(),
                MemberDto.from(article.getMember()),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getCommentCount(),
                article.getLikesCount(),
                article.getThumbnailUrl(),
                null
        );
    }

}
