package com.isack.syp.playlist;

import com.isack.syp.item.Item;
import com.isack.syp.item.ItemDto;
import com.isack.syp.item.ItemService;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.playlist.dto.request.PlaylistRequest;
import com.isack.syp.playlist.dto.response.PlaylistResponse;
import com.isack.syp.playlist.dto.response.PlaylistsResponse;
import com.isack.syp.playlistitem.PlaylistItem;
import com.isack.syp.playlistitem.PlaylistItemRepository;
import com.isack.syp.playlistitem.PlaylistItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistItemService playlistItemService;
    private final PlaylistItemRepository playlistItemRepository;
    private final ItemService itemService;

    public PlaylistResponse findById(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(RuntimeException::new);
        List<Item> items = playlistItemService.findItemsByPlaylistId(playlistId);
        List<ItemDto> itemDtoList = items.stream().map(ItemDto::from).toList();
        return PlaylistResponse.from(playlist, itemDtoList);
    }

    public PlaylistsResponse findByMemberId(Long memberId) {
        List<Playlist> playlists = playlistRepository.findByMemberId(memberId);
        List<PlaylistResponse> playlistResponses = new ArrayList<>();
        for (Playlist playlist : playlists) {
            List<Item> items = playlistItemService.findItemsByPlaylistId(playlist.getId());
            List<ItemDto> itemDtoList = items.stream().map(ItemDto::from).toList();
            PlaylistResponse playlistResponse = PlaylistResponse.from(playlist, itemDtoList);
            playlistResponses.add(playlistResponse);
        }
        return PlaylistsResponse.from(playlistResponses);
    }

    @Transactional
    public Long savePlaylist(PlaylistRequest playlistRequest, Long memberId) {
        List<Item> items = playlistRequest.getItemDtoList().stream().map(ItemDto::toEntity).toList();

        Map<Item, Long> savedItemIds = new HashMap<>();

        for (Item item : items) {
            Long itemId = itemService.saveItem(item);
            savedItemIds.put(item, itemId);
        }

        Playlist playlist = playlistRequest.toEntity(memberId);
        playlistRepository.save(playlist);

        List<PlaylistItem> playlistItems = items.stream().map(item -> PlaylistItem.of(playlist.getId(), savedItemIds.get(item))).toList();
        playlistItemService.saveAllPlaylistItem(playlistItems);

        return playlist.getId();
    }

    @Transactional
    public void deletePlaylist(Long playlistId, MemberDto memberDto) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, playlist);
        playlistRepository.delete(playlist);
        playlistItemService.deletePlaylistItem(playlistId);
    }

    @Transactional
    public void addItem(ItemDto itemDto, Long playlistId, MemberDto memberDto) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, playlist);

        // 새로운 아이템을 ITEM, PLAYLIST_ITEM 테이블에 각각 저장
        Long itemId = itemService.saveItem(itemDto.toEntity());
        if (!playlistItemRepository.existsByPlaylistIdAndItemId(playlistId, itemId)) { // 중복 방지
            PlaylistItem newPlaylistItem = PlaylistItem.of(playlistId, itemId);
            playlistItemRepository.save(newPlaylistItem);
        }
    }

    @Transactional
    public void deleteItem(Long playlistId, Long itemId, MemberDto memberDto) {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, playlist);
        playlistItemService.deleteItemInPlaylist(playlistId, itemId);
    }

    public void validateAuthor(MemberDto memberDto, Playlist playlist) {
        if (!playlist.isAuthor(memberDto.getId())) {
            throw new RuntimeException("작성자가 아닙니다.");
        }
    }
}
