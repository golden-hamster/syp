package com.isack.syp.articleItem;

import com.isack.syp.item.Item;
import com.isack.syp.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ArticleItemService {

    private final ArticleItemRepository articleItemRepository;
    private final ItemRepository itemRepository;

    public List<Item> findItemsByArticleId(long articleId) {
        List<ArticleItem> articleItems = articleItemRepository.findByArticleId(articleId);
        return articleItems.stream().map(articleItem -> itemRepository.findById(articleItem.getItemId()).orElseThrow(RuntimeException::new)).toList();
    }

    @Transactional
    public void saveArticleItem(Long articleId, Long itemId) {
        articleItemRepository.save(ArticleItem.of(articleId, itemId));
    }

    @Transactional
    public void deleteArticleItem(Long articleId) {
        articleItemRepository.deleteByArticleId(articleId);
    }
}
