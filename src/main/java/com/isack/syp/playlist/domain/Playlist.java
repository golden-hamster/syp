package com.isack.syp.playlist.domain;

import lombok.Getter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Entity
public class Playlist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    private String apiId;

    private String thumbnailUrl;

    private Integer likeCount;

    protected Playlist() {}

    private Playlist(String apiId, String thumbnailUrl) {
        this.apiId = apiId;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static Playlist of(String apiId, String thumbnailUrl) {
        return new Playlist(apiId, thumbnailUrl);
    }


}
