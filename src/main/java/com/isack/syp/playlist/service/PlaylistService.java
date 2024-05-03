package com.isack.syp.playlist.service;

import com.isack.syp.feign.YoutubeClient;
import com.isack.syp.feign.dto.response.ThumbnailUrlDto;
import com.isack.syp.playlist.repository.PlaylistRepository;
import com.isack.syp.playlist.domain.Playlist;
import com.isack.syp.playlist.dto.PlaylistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PlaylistService {

    @Value("${apiKey}")
    private String apiKey;

    private final YoutubeClient youtubeClient;
    private final PlaylistRepository playlistRepository;

    @Transactional
    public Playlist savePlaylist(PlaylistDto playlistDto) {
        ThumbnailUrlDto thumbnailUrlDto = youtubeClient.getThumbnailUrl("snippet", 1, playlistDto.getApiId(), apiKey);
        String thumbnailUrl = thumbnailUrlDto.getItems().get(0).getSnippet().getThumbnails().getMedium().getUrl();
        return playlistRepository.save(playlistDto.toEntity(thumbnailUrl));
    }

    public Optional<Playlist> findByApiId(String apiId) {
        return playlistRepository.findByApiId(apiId);
    }

    public PlaylistDto findById(Long id) {
        return playlistRepository.findById(id).map(PlaylistDto::from).orElseThrow(IllegalArgumentException::new);
    }

    public ThumbnailUrlDto getThumbnailUrl(String part, int maxResult, String playlistId, String key) {
        return youtubeClient.getThumbnailUrl(part, maxResult, playlistId, key);
    }

}
