package com.isack.syp.comment.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.comment.domain.Comment;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long id;
    private ArticleDto articleDto;
    private MemberDto memberDto;
    private String content;
    private LocalDateTime createdAt;

    public CommentDto(Long id, ArticleDto articleDto, MemberDto memberDto, String content, LocalDateTime createdAt) {
        this.id = id;
        this.articleDto = articleDto;
        this.memberDto = memberDto;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentDto of(String content) {
        return new CommentDto(null, null, null, content, null);
    }

    public Comment toEntity(Article article, Member member) {
        return Comment.of(article, member, content);
    }
}
