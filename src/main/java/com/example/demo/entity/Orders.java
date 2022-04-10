package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2022-04-05 20:24:36
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 343320271164290335L;
    
    private Integer id;
    
    private String openid;
    
    private String title;
    
    private Integer num;
    
    private String makedate;
    
    private String username;
    
    private String placedate;
    
    private String usertell;
    
    private Integer state;

    private String temperature;

    private String size;

    private Products products;

    public void setSize(String size) {
        this.size = size;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSize() {
        return size;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMakedate() {
        return makedate;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlacedate() {
        return placedate;
    }

    public void setPlacedate(String placedate) {
        this.placedate = placedate;
    }

    public String getUsertell() {
        return usertell;
    }

    public void setUsertell(String usertell) {
        this.usertell = usertell;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setOrderstate(Integer s) {
        this.state = s;
    }
}

