package com.isack.syp.article.controller;

import com.isack.syp.article.dto.response.ArticleResponse;
import com.isack.syp.article.dto.response.ArticlesResponse;
import com.isack.syp.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<ArticlesResponse> findArticles(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleResponse> articles = articleService.findAll(pageable).map(ArticleResponse::from);
        ArticlesResponse articlesResponse = ArticlesResponse.from(articles);
        return ResponseEntity.ok(articlesResponse);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long articleId) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.findById(articleId));
        return ResponseEntity.ok(articleResponse);
    }

}
