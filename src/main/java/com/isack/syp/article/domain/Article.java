package com.isack.syp.article.domain;

import com.isack.syp.audit.AuditingFields;
import com.isack.syp.member.domain.Member;
import com.isack.syp.playlist.domain.Playlist;
import com.isack.syp.playlist.dto.PlaylistDto;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Article extends AuditingFields {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member member;

    private String title;

    private String content;

    private Integer commentsCount;

    private Boolean deleted = Boolean.FALSE;

    protected Article() {}

    private Article(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public static Article of(Member member, String title, String content){
        return new Article(member, title, content);
    }
}
