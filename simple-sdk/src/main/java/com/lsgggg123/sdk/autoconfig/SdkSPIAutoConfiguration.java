package com.lsgggg123.sdk.autoconfig;

import com.lsgggg123.sdk.bean.BeanA;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(StringUtils.class)
public class SdkSPIAutoConfiguration {
    
    @Bean
    public BeanA beanA() {
        return new BeanA();
    }
}