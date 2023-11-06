package com.lsgggg123.http2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/simple/http2")
public class Http2Controller {

    @GetMapping("/demo")
    public Map<String, Object> demo() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("msg", "ok");
        return ret;
    }
}