package com.example.news;

public class CategoryRVModal {
private String category;
private String categoryImageurl;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageurl() {
        return categoryImageurl;
    }

    public void setCategoryImageurl(String categoryImageurl) {
        this.categoryImageurl = categoryImageurl;
    }

    public CategoryRVModal(String category, String categoryImageurl) {
        this.category = category;
        this.categoryImageurl = categoryImageurl;
    }
}
