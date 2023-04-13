package com.lsgggg123.web.task;


public interface BizTask {

    void exec(String input);
    
    default String taskName() {
        return getClass().getSimpleName();
    }
}
