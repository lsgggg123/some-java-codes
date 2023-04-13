package com.lsgggg123.web.controller;

import com.lsgggg123.web.service.BizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/simple/web")
public class DemoController {
    
    @Resource
    protected BizService bizService;
    
    @GetMapping("/demo")
    public Map<String, Object> demo() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("msg", "ok");
        bizService.biz("io");
        return ret;
    }
}