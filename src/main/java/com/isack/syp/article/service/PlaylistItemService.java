package com.isack.syp.article.service;

import com.isack.syp.article.domain.PlaylistItem;
import com.isack.syp.article.dto.PlaylistItemDto;
import com.isack.syp.article.repository.PlaylistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaylistItemService {

    private final PlaylistItemRepository playlistItemRepository;

    public Long savePlaylistItem(PlaylistItem playlistItem) {
        return playlistItemRepository.save(playlistItem).getId();
    }

    public List<PlaylistItemDto> findByArticleId(Long articleId) {
        return playlistItemRepository.findByArticleId(articleId).stream().map(PlaylistItemDto::from).toList();
    }
}


