package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class ArticleUpdateRequest {
    private String title;
    private String content;

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content);
    }
}