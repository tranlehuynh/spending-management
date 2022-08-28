package com.app.service.impl;

import com.app.pojo.Item;
import com.app.repository.ItemRepository;
import com.app.service.ItemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItemsPagination(Map<String, String> params, int page) {
        return this.itemRepository.getItemsPagination(params, page);
    }

    @Override
    public List<Item> getItems() {
        return this.itemRepository.getItems();
    }
}
