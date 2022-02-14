package org.skywalking;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @Author: Gol
 */
public class Interceptor2 {
    @RuntimeType
    public Object intercept(
            @This Object obj, // 目标对象
            @AllArguments Object[] allArguments, // 注入目标方法的全部参数
            @SuperCall Callable<?> zuper, // 调用目标方法，必不可少哦
            @Origin Method method, // 目标方法
            @Super DB db // 目标对象
    ) throws Exception {
        System.out.println(obj);
        System.out.println(db);
        // 从上面两行输出可以看出，obj和db是一个对象
        try {
            return zuper.call();
        } finally {
        }
    }
}
