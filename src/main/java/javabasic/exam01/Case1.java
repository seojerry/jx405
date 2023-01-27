package javabasic.exam01;

public class Case1 {
    public static void main(String[] args) {
        //문제1
        System.out.println("두자리 카프카수");
        for (int i = 10; i <= 99; i++) {
            if(i==(((i%10)+(i/10)))*((i%10)+(i/10))){
                System.out.println(i);
            }
        }
        System.out.println("네자리 카프카수");
        //문제2
        for (int i = 1000; i <= 9999; i++) {
            int a = ((i / 1000) * 10)+((i/100)%10);
            int b = (i % 100);
            if (i==(a+b)*(a+b)) {
                System.out.println(i);
            }
        }
    }
}