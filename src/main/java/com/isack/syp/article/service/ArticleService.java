package com.isack.syp.article.service;

import com.isack.syp.article.dto.ArticleDto;
import com.isack.syp.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<ArticleDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        return articleRepository.findById(id).map(ArticleDto::from).orElseThrow(IllegalArgumentException::new);
    }

}
