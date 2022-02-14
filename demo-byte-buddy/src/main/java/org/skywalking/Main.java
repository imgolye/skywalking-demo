package org.skywalking;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: Gol
 */
public class Main {

    public static void main(String[] args) throws Exception{

        String str = new ByteBuddy()
                .subclass(Object.class)
                .name("org.skywalinkg.Type")
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("link kids"))
                .make()
                // 加载新类型，默认WRAPPER策略
                .load(ByteBuddy.class.getClassLoader())
                .getLoaded()
                .newInstance() // 通过 Java反射创建 com.skywalinkg.Type实例
                .toString(); // 调用 toString()方法
                System.out.println(str);

    }
}
