package com.app.service;

import com.app.pojo.Items;
import java.util.List;
import java.util.Map;

public interface ItemService {
    List<Items> getItems(Map<String, String> params, int page);
}
