package com.isack.syp.article.service;

import com.isack.syp.article.domain.Article;
import com.isack.syp.item.Item;
import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.repository.ArticleQueryRepository;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.articleItem.ArticleItemService;
import com.isack.syp.item.ItemService;
import com.isack.syp.item.itemDto;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final ArticleItemService articleItemService;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    public Page<ArticleDto> findAll(String search, Pageable pageable) {
        return articleQueryRepository.findAll(search, pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        List<Item> items = articleItemService.findItemsByArticleId(id);
        return articleRepository.findById(id).map((article) -> ArticleDto.from(article, items)).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Long saveArticle(ArticleDto articleDto) {
        Member member = memberRepository.findById(articleDto.getMemberDto().getId()).orElseThrow(RuntimeException::new);
        Article article = articleRepository.save(articleDto.toEntity(member));
        List<Item> items = articleDto.getItemDtoList().stream().map((itemDto::toEntity)).toList();
        for (Item item : items) {
            Long itemId = itemService.saveItem(item);
            articleItemService.saveArticleItem(article.getId(), itemId);
        }
        return article.getId();
    }

    @Transactional
    public void deleteArticle(Long articleId, MemberDto memberDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, article);
        articleRepository.delete(article);
        articleItemService.deleteArticleItem(articleId);
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(articleDto.getMemberDto(), article);
        articleItemService.deleteArticleItem(articleId);

        List<Item> items = articleDto.getItemDtoList().stream().map(itemDto::toEntity).toList();
        for (Item item : items) {
            Long itemId = itemService.saveItem(item);
            articleItemService.saveArticleItem(article.getId(), itemId);
        }

        article.updateThumbnailUrl(articleDto.getThumbnailUrl());
        article.updateTitle(articleDto.getTitle());
        article.updateContent(articleDto.getContent());
    }

    private void validateAuthor(MemberDto memberDto, Article article) {
        if (!article.isAuthor(memberDto.getId())) {
            throw new RuntimeException("작성자가 아닙니다."); //TODO: RuntimeException 을 상속받아서 따로 처리할 것
        }
    }

}
