package com.ebite.www.ebite;

/**
 * Created by Ramakant on 08-05-2018.
 */

public class ImageViewModel {
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageTweet(String imageTweet) {
        this.imageTweet = imageTweet;
    }

    public void setImageTweetHandle(String imageTweetHandle) {
        this.imageTweetHandle = imageTweetHandle;
    }

    public void setTweetTime(String tweetTime) {
        this.tweetTime = tweetTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageTweet() {
        return imageTweet;
    }

    public String getImageTweetHandle() {
        return imageTweetHandle;
    }

    public String getTweetTime() {
        return tweetTime;
    }

    public String getSystemTime() {
        return systemTime;
    }

    private String imageTweet;
    private String imageTweetHandle;
    private String tweetTime;
    private String systemTime;
    private String imageUrl;

    public ImageViewModel(){}
}
