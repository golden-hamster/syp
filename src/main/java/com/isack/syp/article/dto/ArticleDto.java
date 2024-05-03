package com.isack.syp.article.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.playlist.domain.Playlist;
import com.isack.syp.playlist.dto.PlaylistDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private Long id;
    private MemberDto memberDto;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private Integer commentCount;
    private PlaylistDto playlistDto;

    public ArticleDto(Long id, MemberDto memberDto, String title, String content, LocalDateTime createdAt, String createdBy, Integer commentsCount, PlaylistDto playlistDto) {
        this.id = id;
        this.memberDto = memberDto;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.commentCount = commentsCount;
        this.playlistDto = playlistDto;
    }

    public static ArticleDto of(MemberDto memberDto, String title, String content, PlaylistDto playlistDto) {
        return new ArticleDto(null, memberDto, title, content, null, null, null, playlistDto);
    }


    public Article toEntity(Member member, Playlist playlist) {
        return Article.of(member, title, content, playlist);
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
                PlaylistDto.from(article.getPlaylist())
        );
    }

}
