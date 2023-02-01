package javabasic.day0131;

import javabasic.day0127.LombokTest;

public class Ex01Lombok {
    public static void main(String[] args) {
        LombokTest l = new LombokTest();
        l.setId(1);
        l.setSomething("Something");

        LombokTest l2 = new LombokTest();
        l2.setId(1);
        l2.setSomething("Something");

        System.out.println(l.equals(l2));
    }
}
