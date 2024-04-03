package com.isack.syp.playlist.dto;

import com.isack.syp.playlist.domain.Playlist;
import lombok.Getter;

@Getter
public class PlaylistDto {
    private Long id;
    private String playlistId;

    public PlaylistDto(Long id, String playlistId) {
        this.id = id;
        this.playlistId = playlistId;
    }

    public static PlaylistDto from(Playlist playlist) {
        return new PlaylistDto(playlist.getId(), playlist.getPlaylistId());
    }
}
