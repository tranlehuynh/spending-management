package com.app.repository;

import com.app.pojo.Items;
import java.util.List;
import java.util.Map;

public interface ItemRepository {
    List<Items> getItems(Map<String, String> params, int page);
}
