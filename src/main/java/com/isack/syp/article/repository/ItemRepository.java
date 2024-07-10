package com.isack.syp.article.repository;

import com.isack.syp.article.domain.item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<item, Long> {
    List<item> findByArticleId(Long articleId);

    void deleteByArticleId(Long articleId);
}
