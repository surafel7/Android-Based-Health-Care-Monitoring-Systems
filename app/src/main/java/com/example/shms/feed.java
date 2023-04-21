package com.example.shms;

public class feed {
    private String userid;
    private String Description;
private String key;
feed(){
    //
}
    public feed(String userid, String description,String key ){
        Description = description;
        this.userid = userid;
        this.key=key;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
