package com.isack.syp.article.domain;

import com.isack.syp.audit.AuditingFields;
import com.isack.syp.member.domain.Member;
import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@SQLDelete(sql = "UPDATE article SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Entity
public class Article extends AuditingFields {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    @Column(length = 1000)
    private String content;

    private Integer commentCount;

    private Integer likesCount;

    private String thumbnailUrl;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "playlist_id")
//    private Playlist playlist;


    private Boolean deleted = Boolean.FALSE;

    protected Article() {}

    private Article(Member member, String title, String content, String thumbnailUrl) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.commentCount = 0;
        this.likesCount = 0;
    }

    public static Article of(Member member, String title, String content, String thumbnailUrl){
        return new Article(member, title, content, thumbnailUrl);
    }

    public void updateThumbnailUrl(String thumbnailUrl){this.thumbnailUrl = thumbnailUrl;}

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void addCommentCount() {
        this.commentCount++;
    }

    public void increaseLikes() {
        this.likesCount++;
    }

    public void decreaseLikes() {
        this.likesCount--;
    }

    public boolean isAuthor(Long memberId) {
        if (memberId == null) {
            return false;
        }
        return member.getId().equals(memberId);
    }

}
