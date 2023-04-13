package com.lsgggg123.web.task;

import org.springframework.stereotype.Component;

@Component
public class Task1 extends AbstractTask {

    @Override
    public void doExecute(String input) {
        System.out.println("Task1 doExecute()");
    }
}
