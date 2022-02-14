package org.skywalking;

/**
 * @Author: Gol
 */
public class Interceptor {

    public static String intercept(int i) {
        return "int";
    }

    public static String intercept(Object o) {
        return "Object";
    }

    /**
     * @Author: Gol
     */
    public static class Foo {
        public String bar(){
            return null;
        }

        public String foo(){
            return null;
        }

        public String foo(Object o){
            return  null;
        }
    }
}
