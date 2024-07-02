package com.isack.syp.article.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.domain.PlaylistItem;
import lombok.Getter;

@Getter
public class PlaylistItemDto {

    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;

    public PlaylistItemDto(String videoId, String videoTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    public PlaylistItem toEntity(Article article) {
        return PlaylistItem.of(videoId, article, videoTitle, thumbnailUrl);
    }

    public static PlaylistItemDto from(PlaylistItem playlistItem) {
        return new PlaylistItemDto(
                playlistItem.getVideoId(),
                playlistItem.getVideoTitle(),
                playlistItem.getThumbnailUrl()
        );
    }

}
