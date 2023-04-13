package com.lsgggg123.sdk.autoconfig;

import com.lsgggg123.sdk.bean.BeanB;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(StringUtils.class)
public class SdkAnnotationAutoConfiguration {

    @Bean
    public BeanB beanB() {
        return new BeanB();
    }
}