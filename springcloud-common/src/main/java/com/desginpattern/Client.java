package com.desginpattern;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        A proxyInstance = (A)new ProxyFactory(new B()).getProxyInstance();
        proxyInstance.a();
    }
}

interface A {void a();}

class B implements A{ public void a() {System.out.println("代理对象");} }//被代理对象/目标对象

class ProxyFactory {
    private Object target;
    public ProxyFactory(Object target) {this.target = target;}
    public Object getProxyInstance() {//代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("前处理....");
                        Object object = method.invoke(target, args);
                        System.out.println("后处理....");
                        return object;
                    }
                });
    }
}



































