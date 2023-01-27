package javabasic.day0110;

import javabasic.util.ScannerUtil;

import java.util.Random;
import java.util.Scanner;

//로또 시뮬레이터
//ver 4.0
//사용자로부터 총 몇게임할지 입력 받은 후에
//해당 게임마다 각각 자동/수동 입력을 받아서
//알맞게 처리하는 프로그램
public class Ex11Lotto04 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int NUMBER_LENGTH = 6;
    public static final int NUMBER_MIN = 1;
    public static final int NUMBER_MAX = 45;

    public static int[] userNumbers = new int[NUMBER_LENGTH];
    public static int[][] userNumbersArray = new int[100][];
    public static int[] computerNumbers = new int[NUMBER_LENGTH];
    public static int[][] computerNumbersArray = new int[100][];
    public static void main(String[] args) {
        System.out.println("몇게임 하실라우?");
        int num = SCANNER.nextInt();
        for (int i = 0; i < num; i++) {
            userNumbersArray[i] = new int[6];
            computerNumbersArray[i] = new int[6];

            String message = "1.자동 2.수동";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                setAutoNumbers(userNumbers);
            } else {
                setManualNumbers(userNumbers);
            }
            sort(userNumbers);

            setAutoNumbers(computerNumbers);
            sort(computerNumbers);

            for (int j = 0; j < 6; j++) {
                userNumbersArray[i][j] = userNumbers[j];
                computerNumbersArray[i][j] = computerNumbers[j];
            }
        }

//        printResult();
        printResults(userNumbersArray,computerNumbersArray,num);

    }

    private static void printResults(int[][] array1,int[][] array2,int num) {
        for (int i = 0;i<num; i++) {
            userNumbers = array1[i];
            computerNumbers = array2[i];
            printResult();
        }
    }

    private static void printResult() {
        System.out.println("사용자의 숫자");
        printArray(userNumbers);

        System.out.println("컴퓨터의 숫자");
        printArray(computerNumbers);

        System.out.println("총 맞은 갯수: "+countSame());
    }

    private static int countSame() {
        int count =0;
        for (int i = 0; i < computerNumbers.length; i++) {
            if (contains(userNumbers, computerNumbers[i])) {
                count++;
            }
        }
        return count;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d", array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }

    private static void setManualNumbers(int[] array) {
        for (int i = 0; i < array.length; ) {
            int temp = ScannerUtil.nextInt(SCANNER, "1~45사이의 숫자를 입력해주세요.", NUMBER_MIN, NUMBER_MAX);
            if (!contains(array, temp)) {
                array[i] = temp;
                i++;
            }else{
                System.out.println("중복된 숫자는 입력하실 수 없습니다.");
            }
        }
    }

    private static void setAutoNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; ) {
            int temp = random.nextInt(NUMBER_MAX) + NUMBER_MIN;
            if(!contains(array,temp)){
                array[i] = temp;
                i++;
            }
        }
    }

    public static boolean contains(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                i = -1;
            }
        }
    }
}
