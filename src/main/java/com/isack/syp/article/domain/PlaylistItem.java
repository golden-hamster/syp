package com.isack.syp.article.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@SQLDelete(sql = "UPDATE playlist_item SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "playlist_item")
@Entity
public class PlaylistItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    private String videoId;

    private String channelTitle;

    private String thumbnailUrl;

    private Boolean deleted = Boolean.FALSE;

    protected PlaylistItem() {}

    private PlaylistItem(String videoId, Article article, String channelTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.article = article;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static PlaylistItem of(String videoId, Article article, String channelTitle, String thumbnailUrl) {
        return new PlaylistItem(videoId, article, channelTitle, thumbnailUrl);
    }

}
