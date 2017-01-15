package com.jackie.activity_04;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/26.
 */
public class News implements Serializable {

    private int id;
    private String image;
    private String title;
    private Date time;
    private String content;

    public News() {
    }
    public News(int id, String title, Date time, String content) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                '}';
    }
}
