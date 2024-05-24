package com.isack.syp.article.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.domain.PlaylistItem;
import lombok.Getter;

@Getter
public class PlaylistItemDto {

    private String videoId;
    private String channelTitle;
    private String thumbnailUrl;

    public PlaylistItemDto(String videoId, String channelTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.channelTitle = channelTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    public PlaylistItem toEntity(Article article) {
        return PlaylistItem.of(videoId, article, channelTitle, thumbnailUrl);
    }

    public static PlaylistItemDto from(PlaylistItem playlistItem) {
        return new PlaylistItemDto(
                playlistItem.getVideoId(),
                playlistItem.getChannelTitle(),
                playlistItem.getThumbnailUrl()
        );
    }

}
