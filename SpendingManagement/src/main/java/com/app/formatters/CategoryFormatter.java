package com.app.formatters;

import com.app.pojo.Category;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class CategoryFormatter implements Formatter<Category> {

    @Override
    public String print(Category t, Locale locale) {
        return String.valueOf(t);
    }

    @Override
    public Category parse(String string, Locale locale) throws ParseException {
        Category c = new Category();
        c.setId(Integer.parseInt(string));
        return c;
    }
    
}
