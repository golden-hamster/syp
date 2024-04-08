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
    private String createdBy;

    public CommentDto(Long id, ArticleDto articleDto, MemberDto memberDto, String content, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.articleDto = articleDto;
        this.memberDto = memberDto;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public static CommentDto of(String content) {
        return new CommentDto(null, null, null, content, null, null);
    }

    public Comment toEntity(Article article, Member member) {
        return Comment.of(article, member, content);
    }

    public static CommentDto from(Comment comment) {
        return new CommentDto(
                comment.getId(),
                ArticleDto.from(comment.getArticle()),
                MemberDto.from(comment.getMember()),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getCreatedBy()
        );
    }

}
