package com.isack.syp.article.service;

import com.isack.syp.article.domain.item;
import com.isack.syp.article.dto.itemDto;
import com.isack.syp.article.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Long saveItem(item item) {
        return itemRepository.save(item).getId();
    }

    public List<itemDto> findByArticleId(Long articleId) {
        return itemRepository.findByArticleId(articleId).stream().map(itemDto::from).toList();
    }
}


