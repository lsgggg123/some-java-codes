package com.lsgggg123.web.task;

import com.lsgggg123.web.aop.Profile;
import org.springframework.stereotype.Component;

@Component
public class Task2 extends AbstractTask {
    
    @Override
    @Profile
    protected void doExecute(String input) {
        System.out.println("Task2 doExecute()");
    }
}
