package com.isack.syp.playlist.controller;

import com.isack.syp.feign.dto.response.ThumbnailUrlDto;
import com.isack.syp.playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/playlists")
@RestController
public class PlaylistController {

    @Value("${apiKey}")
    private String apiKey;

    private final PlaylistService playlistService;

    @GetMapping
    public ThumbnailUrlDto getPlaylist() {
        return playlistService.getThumbnailUrl("snippet", 1, "PLHUkhevQftgHALH-5KjRrGcayDvollcrj", apiKey);
    }

}
