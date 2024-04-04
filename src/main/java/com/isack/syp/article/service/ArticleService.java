package com.isack.syp.article.service;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.repository.ArticleRepository;
import com.isack.syp.member.domain.Member;
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

}
