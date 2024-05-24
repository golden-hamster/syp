package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.dto.PlaylistItemDto;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleRequest {

    private String title;

    private String content;

    // 첫 번재 아이템의 url 이 썸네일의 역할을 함
    private String thumbnailUrl;


    private List<PlaylistItemDto> playlistItemDtoList;


    public ArticleRequest(String title, String content, String thumbnailUrl, List<PlaylistItemDto> playlistItemDtoList) {
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.playlistItemDtoList = playlistItemDtoList;
    }

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content, thumbnailUrl, playlistItemDtoList);
    }

}
