package com.isack.syp.comment.domain;

import com.isack.syp.article.domain.Article;
import com.isack.syp.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comment {

    @GeneratedValue
    @Id
    private Long id;

    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private Boolean deleted = Boolean.FALSE;

    protected Comment() {}

    private Comment(String content, Member member, Article article) {
        this.content = content;
        this.member = member;
        this.article = article;
    }

    public static Comment of(String content, Member member, Article article) {
        return new Comment(content, member, article);
    }
}
