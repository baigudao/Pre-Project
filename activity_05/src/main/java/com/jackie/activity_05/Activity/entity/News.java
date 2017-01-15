package com.jackie.activity_05.Activity.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/2.
 */
public class News implements Serializable {

    private int id;
    private String image;
    private String title;
    private String content;
    private Date time;

    public News() {
    }

    public News(int id, String title, String content, Date time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
