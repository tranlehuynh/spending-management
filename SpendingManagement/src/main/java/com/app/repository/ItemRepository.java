package com.app.repository;

import com.app.pojo.Item;
import java.util.List;
import java.util.Map;

public interface ItemRepository {
    List<Item> getItems();
    
    List<Item> getItemsPagination(Map<String, String> params, int page);
}
