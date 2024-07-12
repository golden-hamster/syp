package com.isack.syp.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        return itemRepository.findByVideoId(item.getVideoId())
                .map(optionalItem -> {
                    if (optionalItem.getDeleted()) {
                        optionalItem.changeStatus();
                    }
                    return optionalItem.getId();
                })
                .orElseGet(() -> itemRepository.save(item).getId());
    }
}


