package com.isack.syp.article.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticlesResponse {
    private Page<SimpleArticleResponse> simpleArticleResponses;

    public static ArticlesResponse from(Page<SimpleArticleResponse> simpleArticleResponses) {
        return new ArticlesResponse(simpleArticleResponses);
    }
}
