package javabasic.day0110;
// 로또번호 추첨기
// ver3.0
// 사용자 숫자 추가

// 사용자로부터 1.자동 2.수동 입력받아서
// 각각에 맞게 메소드를 실행시킨 후
// 컴퓨터의 숫자와 비교해서
// 총 맞은 개수를 출력하는 프로그램을 작성하시오.

import java.util.Random;
import java.util.Scanner;

public class Ex10Lotto03 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        int[] userNum = new int[6];
        int[] computerNum = new int[6];
        int[] lotoNumers = new int[6];

        System.out.print("1.수동 2.자동");
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                System.out.print("숫자 여섯개를 입력하세요.\n>");
                for (int i = 0; i < 6; i++) {
                    userNum[i] = sc.nextInt();
                }
                getResult(userNum,lottoNumberGernerate());
                break;
            case 2:
                computerNum = lottoNumberGernerate();
                getResult(computerNum,lottoNumberGernerate());
                break;
        }

    }
    public static void getResult(int[] user,int[] computer) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (user[i] == computer[i]) {
                count++;
            }
        }
        for (int i = 0; i < user.length; i++) {
            System.out.printf("%d", user[i]);
            if (i < user.length - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
        for (int i = 0; i < computer.length; i++) {
            System.out.printf("%d", computer[i]);
            if (i < computer.length - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
        System.out.printf("총 %d개 맞으셨습니다!", count);
    }

    public static int[] lottoNumberGernerate(){
        Random random = new Random();
        int[] lottoNumbers = new int[6];
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
        return lottoNumbers;
    }
}
