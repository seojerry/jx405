package javabasic.exam01;

public class Case2 {
    public static void main(String[] args) {

        for (int i = 2; i < 1000; i++) {
            int count = 0;
            boolean flag = true;
            for (int j = 1; j <= i; j++) {
                if(i%j==0) count++;
                if(count>2){
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.println(i);
        }
    }
}
