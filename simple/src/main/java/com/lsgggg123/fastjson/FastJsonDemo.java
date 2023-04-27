package com.lsgggg123.fastjson;

import com.alibaba.fastjson.JSON;
import com.lsgggg123.pojo.PoJo;

public class FastJsonDemo {
    
    public static void main(String[] args) {
        String json = JSON.toJSONString(PoJo.newRandomPojo());
        System.out.println(json);
    }
}