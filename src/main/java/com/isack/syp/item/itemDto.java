package com.isack.syp.item;

import com.isack.syp.article.domain.Article;
import lombok.Getter;

@Getter
public class itemDto {

    private String videoId;
    private String videoTitle;
    private String thumbnailUrl;

    public itemDto(String videoId, String videoTitle, String thumbnailUrl) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Item toEntity() {
        return Item.of(videoId, videoTitle, thumbnailUrl);
    }

    public static itemDto from(Item item) {
        return new itemDto(
                item.getVideoId(),
                item.getVideoTitle(),
                item.getThumbnailUrl()
        );
    }

}
