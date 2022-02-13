package org.skywalking;
import java.lang.instrument.Instrumentation;

/**
 * @Author: Gol
 */
public class TestAgent {

    public static void agentmain(String[] args, Instrumentation inst) throws  Exception{

        // 注册一个 Transformer，该 Transformer在类加载时被调用
        inst.addTransformer(new org.skywalking.Transformer(), true);
        inst.retransformClasses(TestClass.class);
        System.out.println("premain done");
    }
    public static void premain(String agentArgs) {
        System.out.println("this is a java agent only one args");
        System.out.println("参数:" + agentArgs + "\n");
    }

}
