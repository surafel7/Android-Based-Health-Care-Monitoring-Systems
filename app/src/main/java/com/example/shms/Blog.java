package com.example.shms;

public class Blog {
    private String Title;
    private String Category;
    private String Description;
    private String Image;
private String Uid ;
Blog(){
    //
}
    public Blog(String title, String category, String description, String image,String uid) {
        Title = title;
        Category = category;
        Description = description;
        Image = image;
        this.Uid=uid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
