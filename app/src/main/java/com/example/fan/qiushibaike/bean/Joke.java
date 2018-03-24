package com.example.fan.qiushibaike.bean;

/**
 * Created by Fan on 2018/3/23.
 */

public class Joke {
    private String userName;
    private String userImageUrl;
    private String content;
    private String pictureUrl;

    public Joke(String userName,String userImageUrl,String content,String pictureUrl){
        this.userName = userName;
        this.userImageUrl = userImageUrl;
        this.content = content;
        this.pictureUrl = pictureUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
