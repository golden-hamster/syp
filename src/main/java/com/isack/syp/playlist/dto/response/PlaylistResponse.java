package com.isack.syp.playlist.dto.response;

import com.isack.syp.item.ItemDto;
import com.isack.syp.playlist.Playlist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaylistResponse {
    private Long id;
    private String title;
    private String createdBy;
    private String createdAt;
    private List<ItemDto> items;

    public static PlaylistResponse from(Playlist playlist, List<ItemDto> items) {
        return new PlaylistResponse(
                playlist.getId(),
                playlist.getTitle(),
                playlist.getCreatedBy(),
                playlist.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분")),
                items);
    }
}
