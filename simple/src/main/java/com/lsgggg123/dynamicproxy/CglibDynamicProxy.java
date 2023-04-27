package com.lsgggg123.dynamicproxy;

import com.lsgggg123.pojo.PoJo;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxy {
    
    public static void main(String[] args) {
        PoJo poJo = new PoJo();
        poJo.setId(1L);
        poJo.setName("new Pojo");
        System.out.println(poJo.displayName());
    
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PoJo.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (!method.getName().endsWith("displayName")) {
                    return methodProxy.invokeSuper(target, objects);
                }
                
                System.out.println("before");
                Object result = methodProxy.invokeSuper(target, objects);
                System.out.println("after");
                return result;
            }
        });
        PoJo proxyPojo = (PoJo) enhancer.create();
        proxyPojo.setId(1L);
        proxyPojo.setName("proxy Pojo");
        System.out.println(proxyPojo.displayName());
    }
}