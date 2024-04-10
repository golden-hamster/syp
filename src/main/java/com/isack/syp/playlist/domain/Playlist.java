package com.isack.syp.playlist.domain;

import com.isack.syp.article.domain.Article;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Playlist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String playlistId;

    private Boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
