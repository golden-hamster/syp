package com.isack.syp.article.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ArticlesResponse {
    private Page<ArticleResponse> articleResponses;

    public ArticlesResponse(Page<ArticleResponse> articleResponse) {
        this.articleResponses = articleResponse;
    }

    public static ArticlesResponse from(Page<ArticleResponse> articleResponses) {
        return new ArticlesResponse(articleResponses);
    }
}
