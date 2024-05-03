package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.playlist.dto.PlaylistDto;
import lombok.Getter;

@Getter
public class ArticleRequest {
    private String title;
    private String content;
    private String apiId;

    public ArticleRequest(String title, String content, String apiId) {
        this.title = title;
        this.content = content;
        this.apiId = apiId;
    }

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content, PlaylistDto.of(apiId));
    }

}
