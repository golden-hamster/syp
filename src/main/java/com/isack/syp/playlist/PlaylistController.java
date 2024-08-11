package com.isack.syp.playlist;

import com.isack.syp.item.ItemDto;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.playlist.dto.request.PlaylistRequest;
import com.isack.syp.playlist.dto.response.PlaylistResponse;
import com.isack.syp.playlist.dto.response.PlaylistsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/playlists")
@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistResponse> findPlaylist(@PathVariable Long playlistId) {
        PlaylistResponse playlistResponse = playlistService.findById(playlistId);
        return ResponseEntity.ok(playlistResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<PlaylistsResponse> findMyPlaylists(@AuthenticationPrincipal MemberDto memberDto) {
        PlaylistsResponse playlistsResponse = playlistService.findByMemberId(memberDto.getId());
        return ResponseEntity.ok(playlistsResponse);
    }

    @PostMapping
    public ResponseEntity<Void> savePlaylist(@RequestBody PlaylistRequest playlistRequest, @AuthenticationPrincipal MemberDto memberDto) {
        Long playlistId = playlistService.savePlaylist(playlistRequest, memberDto.getId());
        return ResponseEntity.created(URI.create("/api/playlists/" + playlistId)).build();
    }

    @PostMapping("/{playlistId}")
    public ResponseEntity<Void> addItem(@RequestBody ItemDto itemDto, @PathVariable Long playlistId, @AuthenticationPrincipal MemberDto memberDto) {
        playlistService.addItem(itemDto, playlistId, memberDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{playlistId}/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long playlistId, @PathVariable Long itemId, @AuthenticationPrincipal MemberDto memberDto) {
        playlistService.deleteItem(playlistId, itemId, memberDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long playlistId, @AuthenticationPrincipal MemberDto memberDto) {
        playlistService.deletePlaylist(playlistId, memberDto);
        return ResponseEntity.noContent().build();
    }

}
