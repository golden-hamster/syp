package com.isack.syp.article.service;

import com.isack.syp.article.domain.Article;
import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.member.domain.Member;
import com.isack.syp.member.dto.MemberDto;
import com.isack.syp.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public Page<ArticleDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        return articleRepository.findById(id).map(ArticleDto::from).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Long saveArticle(ArticleDto articleDto) {
        Member member = memberRepository.findById(articleDto.getMemberDto().getId()).orElseThrow(IllegalArgumentException::new);
        return articleRepository.save(articleDto.toEntity(member)).getId();
    }

    @Transactional
    public void deleteArticle(Long articleId, MemberDto memberDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        validateAuthor(memberDto, article);
        articleRepository.delete(article);
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(RuntimeException::new);
        validateAuthor(articleDto.getMemberDto(), article);
        article.updateTitle(articleDto.getTitle());
        article.updateContent(articleDto.getContent());
    }

    private void validateAuthor(MemberDto memberDto, Article article) {
        if (!article.isAuthor(memberDto.getId())) {
            throw new RuntimeException("작성자가 아닙니다."); //TODO: RuntimeException 을 상속받아서 따로 처리할 것
        }
    }
}
