package org.skywalking;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

/**
 * @Author: Gol
 */
public class Interceptor6 {
    @RuntimeType
    public void intercept(@This Object obj,
                          @AllArguments Object[] allArguments) {
        System.out.println("after constructor!");
    }
}
