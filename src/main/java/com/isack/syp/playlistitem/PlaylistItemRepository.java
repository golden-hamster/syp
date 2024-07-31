package com.isack.syp.playlistitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {

    List<PlaylistItem> findByPlaylistId(Long playlistId);

    boolean existsByPlaylistIdAndItemId(Long playlistId, Long itemId);

    void deleteByPlaylistIdAndItemId(Long playlistId, Long itemId);
}
