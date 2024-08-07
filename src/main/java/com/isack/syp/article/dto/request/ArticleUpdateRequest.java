package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.item.ItemDto;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleUpdateRequest {
    private String title;
    private String content;
    private String thumbnailUrl;
    private List<ItemDto> itemDtoList;

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content, thumbnailUrl, itemDtoList);
    }
}
