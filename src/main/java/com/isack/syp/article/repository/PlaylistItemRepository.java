package com.isack.syp.article.repository;

import com.isack.syp.article.domain.PlaylistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {
    List<PlaylistItem> findByArticleId(Long articleId);

    void deleteByArticleId(Long articleId);
}
