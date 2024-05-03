package com.isack.syp.feign;

import com.isack.syp.feign.dto.response.ThumbnailUrlDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "youtubeClient", url = "https://www.googleapis.com/youtube/v3")
public interface YoutubeClient {

    @GetMapping("/playlistItems")
    ThumbnailUrlDto getThumbnailUrl(@RequestParam String part, @RequestParam int maxResults, @RequestParam String playlistId, @RequestParam String key);
}
