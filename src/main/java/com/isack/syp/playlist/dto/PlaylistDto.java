package com.isack.syp.playlist.dto;

import com.isack.syp.playlist.domain.Playlist;
import lombok.Getter;

@Getter
public class PlaylistDto {
    private Long id;
    private String apiId;
    private String thumbnailUrl;

    public PlaylistDto(Long id, String apiId, String thumbnailUrl) {
        this.id = id;
        this.apiId = apiId;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static PlaylistDto of(String apiId) {
        return new PlaylistDto(null, apiId, null);
    }

    public static PlaylistDto from(Playlist playlist) {
        return new PlaylistDto(playlist.getId(), playlist.getApiId(), playlist.getThumbnailUrl());
    }

    public Playlist toEntity(String thumbnailUrl) {
        return Playlist.of(apiId, thumbnailUrl);
    }
}
