package com.isack.syp.article.controller;

import com.isack.syp.article.dto.request.ArticleRequest;
import com.isack.syp.article.dto.request.ArticleUpdateRequest;
import com.isack.syp.article.dto.response.ArticleResponse;
import com.isack.syp.article.dto.response.ArticlesResponse;
import com.isack.syp.article.dto.response.SimpleArticleResponse;
import com.isack.syp.article.service.ArticleService;
import com.isack.syp.likes.LikesService;
import com.isack.syp.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final LikesService likesService;

    @GetMapping
    public ResponseEntity<ArticlesResponse> findArticles(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SimpleArticleResponse> articles = articleService.findAll(search, pageable).map(SimpleArticleResponse::from);
        ArticlesResponse articlesResponse = ArticlesResponse.from(articles);
        return ResponseEntity.ok(articlesResponse);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        boolean isLiked = false;
        if (memberDto != null) {
            isLiked = likesService.isLiked(articleId, memberDto);
        }
        ArticleResponse articleResponse = ArticleResponse.from(articleService.findById(articleId), isLiked);
        return ResponseEntity.ok(articleResponse);
    }

    @PostMapping
    public ResponseEntity<Void> saveArticle(@RequestBody ArticleRequest articleRequest, @AuthenticationPrincipal MemberDto memberDto) {
        Long articleId = articleService.saveArticle(articleRequest.toDto(memberDto));
        return ResponseEntity.created(URI.create("/api/articles/" + articleId)).build();
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Void> updateArticle(@PathVariable Long articleId,
                                              @RequestBody ArticleUpdateRequest articleUpdateRequest,
                                              @AuthenticationPrincipal MemberDto memberDto) {
        articleService.updateArticle(articleId, articleUpdateRequest.toDto(memberDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        articleService.deleteArticle(articleId, memberDto);
        return ResponseEntity.noContent().build();
    }

}
