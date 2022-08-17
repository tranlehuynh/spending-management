package com.app.service;

import com.app.pojo.Item;
import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Item> getItems(Map<String, String> params, int page);
    List<Item> getItemsNo();
}
