package com.example.hasee.common.net.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * list 集合
 * 玩 -- banner
 */
public class WanHomeBannerResponse implements Serializable {
    /**
     * desc : 一起来做个App吧
     * id : 10
     * imagePath : https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
     * isVisible : 1
     * order : 2
     * title : 一起来做个App吧
     * type : 0
     * url : http://www.wanandroid.com/blog/show/2
     */

    @SerializedName("desc")
    private String desc;
    @SerializedName("id")
    private int id;
    @SerializedName("imagePath")
    private String imagePath;
    @SerializedName("isVisible")
    private int isVisible;
    @SerializedName("order")
    private int order;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private int type;
    @SerializedName("url")
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
