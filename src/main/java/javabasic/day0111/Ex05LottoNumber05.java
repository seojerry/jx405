package javabasic.day0111;
//로또 번호 시뮬레이터
//ver 5.0
//동적할당 배열을 사용한 버전


import javabasic.util.ArrayUtil;

import java.util.Random;

public class Ex05LottoNumber05 {
    public static void main(String[] args) {
        int[] lottoNumbers = new int[0];
        Random random = new Random();
        while (ArrayUtil.size(lottoNumbers) < 6) {
            int temp = random.nextInt(45) + 1;
            if (!ArrayUtil.contains(lottoNumbers, temp)) {
                lottoNumbers = ArrayUtil.add(lottoNumbers, temp);
            }
        }
        ArrayUtil.sort(lottoNumbers);

        for (int i = 0; i < lottoNumbers.length; i++) {
            System.out.println(lottoNumbers[i]);
        }
    }
}
