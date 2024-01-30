package com.lsgggg123.web.service;

import com.lsgggg123.web.task.BizTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizService {
    
    @Autowired
    private List<BizTask> tasks;
    
    public void biz(String input) {
        for (BizTask task : tasks) {
            task.exec(input);
        }
    }
}
