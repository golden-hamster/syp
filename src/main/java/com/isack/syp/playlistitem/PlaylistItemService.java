package com.isack.syp.playlistitem;

import com.isack.syp.item.Item;
import com.isack.syp.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaylistItemService {

    private final PlaylistItemRepository playlistItemRepository;
    private final ItemService itemService;

    public List<Item> findItemsByPlaylistId(Long playlistId) {
        List<PlaylistItem> playlistItems = playlistItemRepository.findByPlaylistId(playlistId);
        return playlistItems.stream().map(playlistItem -> itemService.findById(playlistItem.getItemId())).toList();
    }

    @Transactional
    public void saveAllPlaylistItem(List<PlaylistItem> playlistItems) {
        playlistItemRepository.saveAll(playlistItems);
    }

    @Transactional
    public void deletePlaylistItem(Long playlistId) {
        List<PlaylistItem> playlistItems = playlistItemRepository.findByPlaylistId(playlistId);
        playlistItemRepository.deleteAll(playlistItems);
    }

    @Transactional
    public void deleteItemInPlaylist(Long playlistId, Long itemId) {
        playlistItemRepository.deleteByPlaylistIdAndItemId(playlistId, itemId);
    }
}
