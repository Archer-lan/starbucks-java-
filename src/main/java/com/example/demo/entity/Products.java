package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Products)实体类
 *
 * @author makejava
 * @since 2022-04-05 17:00:50
 */
public class Products implements Serializable {
    private static final long serialVersionUID = -36917340720473122L;
    
    private Integer id;
    
    private String imageurl;
    
    private String title;
    
    private Integer price;
    
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

