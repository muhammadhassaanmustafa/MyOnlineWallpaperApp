package com.hmdevs.onlinewallpaperapp.Model;

public class CategoryItem {

    public  String imagelink,name;

    public CategoryItem(String imagelink, String name) {
        this.imagelink = imagelink;
        this.name = name;
    }

    public CategoryItem()
    {

    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
