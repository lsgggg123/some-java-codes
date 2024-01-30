package com.lsgggg123.web.task;

import com.lsgggg123.web.common.PrepareService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractTask implements BizTask {
    
    @Autowired
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
