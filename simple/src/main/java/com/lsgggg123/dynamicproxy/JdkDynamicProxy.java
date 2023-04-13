package com.lsgggg123.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy {
    
    public static void main(String[] args) {
        StringApi simpleStringApi = String::toUpperCase;
        StringApi proxy = LogProxy.getProxy(simpleStringApi);
        proxy.trans("abc");
    }
    
    static class LogProxy implements InvocationHandler {
        private final StringApi target; 
    
        LogProxy(StringApi stringApi) {
            target = stringApi;
        }
    
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long duration = System.currentTimeMillis() - start;
            System.out.println(String.format("StringApi before '%s', after '%s', cost: %d", args[0], result.toString(), duration));
            return result;
        }
        
        static StringApi getProxy(StringApi target) {
            return (StringApi) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new LogProxy(target));
        }
    }
    
    interface StringApi {
        String trans(String input);
    }
}