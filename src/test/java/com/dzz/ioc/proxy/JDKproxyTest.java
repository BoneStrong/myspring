package com.dzz.ioc.proxy;

import com.dzz.ioc.pojo.Girl;
import com.dzz.ioc.pojo.Lishishi;
import com.dzz.ioc.pojo.Shishi;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public class JDKproxyTest {

    @Test
    public void test(){

        Shishi haha = new Shishi().setName("haha");
        Girl o = (Girl) Proxy.newProxyInstance(haha.getClass().getClassLoader(), haha.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object object, Method method, Object[] args) throws Throwable {
                System.out.println("is ready proxy?");
                Object invoke = method.invoke(haha, args);
                System.out.println("end ...");
                return invoke;
            }
        });
        o.setName("hehe");
        System.out.println(o.getName());
    }

    @Test
    public void cglib(){
        Enhancer enhancer=new Enhancer();
        //设置目标代理类
        enhancer.setSuperclass(Lishishi.class);
        //设置方法拦截器
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("is ready cglib ...");
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("cglib end 。。。");
                return invoke;
            }
        });
        Lishishi lishishi = (Lishishi) enhancer.create();
        lishishi.setName("李师师");
        System.out.println(lishishi.getName());
    }
}
