package com.isack.syp.feign.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ThumbnailUrlDto {
    private List<PlaylistItemDto> items;

    @Getter
    public static class PlaylistItemDto{
        private SnippetDto snippet;

        @Getter
        public static class SnippetDto{
            private ThumbnailsDto thumbnails;

            @Getter
            public static class ThumbnailsDto{
                private MediumDto medium;

                @Getter
                public static class MediumDto{
                    private String url;
                }
            }
        }
    }
}
