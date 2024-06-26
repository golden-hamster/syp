package com.isack.syp.comment.domain;

import com.isack.syp.article.domain.Article;
import com.isack.syp.audit.AuditingFields;
import com.isack.syp.member.domain.Member;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Comment extends AuditingFields {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    private Boolean deleted = Boolean.FALSE;

    protected Comment() {}

    private Comment(Article article, Member member, String content) {
        this.article = article;
        this.member = member;
        this.content = content;
    }

    public static Comment of(Article article, Member member, String content) {
        return new Comment(article, member, content);
    }

    public boolean isAuthor(Long memberId) {
        if (memberId == null) {
            return false;
        }
        return member.getId().equals(memberId);
    }
}
