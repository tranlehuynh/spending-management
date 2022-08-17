package com.app.repository;

import com.app.pojo.Item;
import java.util.List;
import java.util.Map;

public interface ItemRepository {
    List<Item> getItems(Map<String, String> params, int page);
    List<Item> getItemsNo();
}
