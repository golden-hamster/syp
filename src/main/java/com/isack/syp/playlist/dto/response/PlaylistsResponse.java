package com.isack.syp.playlist.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaylistsResponse {
    private List<PlaylistResponse> playlistResponses;

    public static PlaylistsResponse from(List<PlaylistResponse> playlistResponses) {
        return new PlaylistsResponse(playlistResponses);
    }
}
