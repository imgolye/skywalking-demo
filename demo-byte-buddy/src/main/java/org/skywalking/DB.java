package org.skywalking;

/**
 * @Author: Gol
 */
public class DB {

    public DB(String name) {

        System.out.println("DB:" + name);
    }

    public String hello(String name) {
        System.out.println("DB:" + name);
        return null;
    }
}
