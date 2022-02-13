package org.skywalking;

/**
 * @Author: Gol
 */
public class Main {
    
    public static void main(String[] args) throws Exception {

        System.out.println(new TestClass().getNumber());
        while (true){
            Thread.sleep(1000);
            System.out.println(new TestClass().getNumber());
        }

    }
}
