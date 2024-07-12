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
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Page<ArticleDto> findAll(String search, Pageable pageable) {
        return articleQueryRepository.findAll(search, pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        List<item> items = itemRepository.findByArticleId(id);
        return articleRepository.findById(id).map((article) -> ArticleDto.from(article, items)).orElseThrow(RuntimeException::new);
    }


    @Transactional
    public Long saveArticle(ArticleDto articleDto) {
        Member member = memberRepository.findById(articleDto.getMemberDto().getId()).orElseThrow(RuntimeException::new);
        Article article = articleRepository.save(articleDto.toEntity(member));
        List<item> items = articleDto.getItemDtoList().stream().map((itemDto -> itemDto.toEntity(article))).toList();
        itemRepository.saveAll(items);
        return article.getId();
    }


    @Transactional
    public void deleteArticle(Long articleId, MemberDto memberDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, article);
        itemRepository.deleteByArticleId(articleId);
        articleRepository.delete(article);
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(articleDto.getMemberDto(), article);
        itemRepository.deleteByArticleId(articleId);
        List<item> byArticleId = itemRepository.findByArticleId(articleId);
        for (item item : byArticleId) {
            log.info("아이템={}", item.getVideoTitle());
        }
        List<item> items = articleDto.getItemDtoList().stream().map((itemDto -> itemDto.toEntity(article))).toList();
        itemRepository.saveAll(items);

        article.updateThumbnailUrl(articleDto.getThumbnailUrl());
        article.updateTitle(articleDto.getTitle());
        article.updateContent(articleDto.getContent());
    }

    private void validateAuthor(MemberDto memberDto, Article article) {
        if (!article.isAuthor(memberDto.getId())) {
            throw new RuntimeException("작성자가 아닙니다."); //TODO: RuntimeException 을 상속받아서 따로 처리할 것
        }
    }

//    private Playlist getOrCreatePlaylist(PlaylistDto playlistDto) {
//        Optional<Playlist> playlist = playlistService.findByApiId(playlistDto.getApiId());
//        return playlist.orElseGet(() -> playlistService.savePlaylist(playlistDto));
//    }
}
