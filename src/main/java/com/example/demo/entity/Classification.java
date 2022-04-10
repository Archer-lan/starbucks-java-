package com.example.demo.entity;

import java.io.Serializable;

/**
 * (Classification)实体类
 *
 * @author makejava
 * @since 2022-04-07 19:43:44
 */
public class Classification implements Serializable {
    private static final long serialVersionUID = -76419574065729426L;
    
    private Integer id;
    
    private String typename;
    
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

