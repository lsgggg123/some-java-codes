package com.lsgggg123.web.task;

import com.lsgggg123.web.common.PrepareService;

import javax.annotation.Resource;

public abstract class AbstractTask implements BizTask {
    
    @Resource
    protected PrepareService prepareService;

    @Override
    public final void exec(String input) {
        prepare();
        doExecute(input);        
    }

    protected abstract void doExecute(String input);

    
    private void prepare() {
        prepareService.prepare();
    }
}
