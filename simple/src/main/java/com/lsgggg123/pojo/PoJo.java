package com.lsgggg123.pojo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class PoJo {
    
    public static PoJo newRandomPojo() {
        PoJo poJo = new PoJo();
        poJo.setId(RandomUtils.nextLong(0, 1000));
        poJo.setName("randName-" + RandomStringUtils.randomAlphanumeric(5));
        return poJo;
    }
    
    private Long id;
    
    private String name;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String displayName() {
        return "PoJo{" + "id:" + id + ", name:\"" + name + '\"' + '}';
    }
}