package org.skywalking;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.takesArguments;

/**
 * Byte Buddy 动态修改方法返回值
 * @Author: Gol
 */
public class MainFoo {
    
    public static void main(String[] args) throws Exception {
        Interceptor.Foo dynamicFoo = new ByteBuddy()
                .subclass(Interceptor.Foo.class)
                .method(isDeclaredBy(Interceptor.Foo.class))
                .intercept(FixedValue.value("One!"))
                .method(named("foo"))
                .intercept(FixedValue.value("Two!"))
                .method(named("foo").and(takesArguments(1)))
                // 参数的方法
                .intercept(FixedValue.value("Three!"))
                .make()
                .load(MainFoo.class.getClassLoader(), INJECTION)
                .getLoaded()
                .newInstance();
        System.out.println(dynamicFoo.bar());
        System.out.println(dynamicFoo.foo());
        System.out.println(dynamicFoo.foo(null));


        

    }

}
