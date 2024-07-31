package com.isack.syp.playlist.dto.request;

import com.isack.syp.item.ItemDto;
import com.isack.syp.playlist.Playlist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class PlaylistRequest {

    private String title;

    private String thumbnailUrl;

    private List<ItemDto> itemDtoList;

    public Playlist toEntity(Long memberId) {
        return Playlist.of(title, thumbnailUrl, memberId);
    }

}
