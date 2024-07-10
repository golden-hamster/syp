package com.isack.syp.article.dto;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.domain.item;
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

    public item toEntity(Article article) {
        return item.of(videoId, article, videoTitle, thumbnailUrl);
    }

    public static itemDto from(item item) {
        return new itemDto(
                item.getVideoId(),
                item.getVideoTitle(),
                item.getThumbnailUrl()
        );
    }

}
