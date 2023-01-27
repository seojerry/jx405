package javabasic.day0110;

import java.util.Random;

public class Ex09Lotto02 {
    public static void main(String[] args) {
        Random random = new Random();
        for (int num=0;num<5;num++) {
            int[] lottoNumbers = new int[6];
            //중복되지 않는 숫자를 배열에 부여하기
            for (int i = 0; i < lottoNumbers.length; ) {
                int temp = random.nextInt(45) + 1;
                boolean numberSwitch = true;
                for (int j = 0; j < i; j++) {
                    if (temp == lottoNumbers[j]) {
                        numberSwitch = false;
                    }
                }

                if (numberSwitch) {
                    lottoNumbers[i] = temp;
                    i++;
                }
            }
            for (int i = 0; i < lottoNumbers.length - 1; i++) {
                if (lottoNumbers[i] > lottoNumbers[i + 1]) {
                    int temp = lottoNumbers[i];
                    lottoNumbers[i] = lottoNumbers[i + 1];
                    lottoNumbers[i + 1] = temp;
                    i = -1;
                }
            }
            for (int i = 0; i < lottoNumbers.length; i++) {
                System.out.printf("%d", lottoNumbers[i]);
                if (i < lottoNumbers.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println();
                }
            }
        }
    }
}
