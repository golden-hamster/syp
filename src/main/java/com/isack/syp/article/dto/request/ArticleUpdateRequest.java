package com.isack.syp.article.dto.request;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.dto.PlaylistItemDto;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.playlist.dto.PlaylistDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleUpdateRequest {
    private String title;
    private String content;
    private String thumbnailUrl;
    private List<PlaylistItemDto> playlistItemDtoList;

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(memberDto, title, content, thumbnailUrl, playlistItemDtoList);
    }
}
