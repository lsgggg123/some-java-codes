package com.lsgggg123.web.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrepareService {
    public void prepare() {
        log.info("Prepare finish");
    }
}