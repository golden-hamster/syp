package com.isack.syp.articleItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleItemRepository extends JpaRepository<ArticleItem, Long> {

    List<ArticleItem> findByArticleId(Long articleId);

    void deleteByArticleId(Long articleId);

}
