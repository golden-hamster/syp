package com.isack.syp.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ItemDto {

    private Long id;
    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;

    public Item toEntity() {
        return Item.of(videoId, videoTitle, thumbnailUrl);
    }

    public static ItemDto from(Item item) {
        return new ItemDto(
                item.getId(),
                item.getVideoId(),
                item.getVideoTitle(),
                item.getThumbnailUrl()
        );
    }

}
