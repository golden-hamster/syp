package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class ArticleRequest {
    private String title;
    private String content;

    public ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content);
    }
}
