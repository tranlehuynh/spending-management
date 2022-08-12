package com.app.controllers;

import com.app.service.CategoryService;
import com.app.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        List<String> categories = new ArrayList<>();
        model.addAttribute("categories", this.categoryService.getCategories());
        
        List<String> items = new ArrayList<>();
        model.addAttribute("items", this.itemService.getItems(params, 0));     
        return "index";
    }
}
