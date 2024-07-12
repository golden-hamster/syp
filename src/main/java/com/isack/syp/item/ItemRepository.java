package com.isack.syp.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE NOT EXISTS (SELECT ai FROM ArticleItem as ai WHERE ai.itemId = i.id)")
    List<Item> findOrphanItems();

    boolean existsByVideoId(String videoId);

    Optional<Item> findByVideoId(String videoId);
}
