package com.isack.syp.article.service;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.domain.PlaylistItem;
import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.dto.PlaylistItemDto;
import com.isack.syp.article.repository.ArticleQueryRepository;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.article.repository.PlaylistItemRepository;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import com.isack.syp.playlist.domain.Playlist;
import com.isack.syp.playlist.dto.PlaylistDto;
import com.isack.syp.playlist.repository.PlaylistRepository;
import com.isack.syp.playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleQueryRepository articleQueryRepository;
    private final MemberRepository memberRepository;
    private final PlaylistItemService playlistItemService;
    private final PlaylistItemRepository playlistItemRepository;

//    public Page<ArticleDto> findAll(Pageable pageable) {
//        return articleRepository.findAll(pageable).map(ArticleDto::from);
//    }

    public Page<ArticleDto> findAll(String search, Pageable pageable) {
        return articleQueryRepository.findAll(search, pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        List<PlaylistItem> playlistItems = playlistItemRepository.findByArticleId(id);
        return articleRepository.findById(id).map((article) -> ArticleDto.from(article, playlistItems)).orElseThrow(RuntimeException::new);
    }

//    @Transactional
//    public Long saveArticle(ArticleDto articleDto) {
//        Playlist playlist = getOrCreatePlaylist(articleDto.getPlaylistDto());
//        Member member = memberRepository.findById(articleDto.getMemberDto().getId()).orElseThrow(IllegalArgumentException::new);
//        return articleRepository.save(articleDto.toEntity(member, playlist)).getId();
//    }

    @Transactional
    public Long saveArticle(ArticleDto articleDto) {
//        List<PlaylistItemDto> playlistItemDtoList = articleDto.getPlaylistItemDtoList();
//        List<PlaylistItem> playlistItems = playlistItemDtoList.stream().map(PlaylistItemDto::toEntity).collect(Collectors.toList());
//        playlistItems.forEach(playlistItemService::savePlaylistItem);
        Member member = memberRepository.findById(articleDto.getMemberDto().getId()).orElseThrow(RuntimeException::new);
        Article article = articleRepository.save(articleDto.toEntity(member));
        List<PlaylistItem> playlistItems = articleDto.getPlaylistItemDtoList().stream().map((playlistItemDto -> playlistItemDto.toEntity(article))).toList();
        playlistItemRepository.saveAll(playlistItems);
        return article.getId();
    }


    @Transactional
    public void deleteArticle(Long articleId, MemberDto memberDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(memberDto, article);
//        List<PlaylistItem> playlistItems = playlistItemRepository.findByArticleId(articleId);
//        playlistItemRepository.deleteAll(playlistItems);
        playlistItemRepository.deleteByArticleId(articleId);
        articleRepository.delete(article);
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(articleDto.getMemberDto(), article);
//        List<PlaylistItem> playlistItems = playlistItemRepository.findByArticleId(articleId);
//        playlistItemRepository.deleteAll(playlistItems);
        playlistItemRepository.deleteByArticleId(articleId);
        List<PlaylistItem> playlistItems = articleDto.getPlaylistItemDtoList().stream().map((playlistItemDto -> playlistItemDto.toEntity(article))).toList();
        playlistItemRepository.saveAll(playlistItems);

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
