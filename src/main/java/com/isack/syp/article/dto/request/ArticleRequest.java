package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.item.itemDto;
import com.isack.syp.member.dto.MemberDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRequest {

    private String title;

    private String content;

    // 첫 번재 아이템의 url 이 썸네일의 역할을 함
    private String thumbnailUrl;


    private List<itemDto> itemDtoList;

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content, thumbnailUrl, itemDtoList);
    }

}
