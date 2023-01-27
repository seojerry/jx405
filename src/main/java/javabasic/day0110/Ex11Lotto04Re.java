package javabasic.day0110;

import javabasic.util.ScannerUtil;

import java.util.Scanner;

public class Ex11Lotto04Re {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int ARRAY_LENGTH =6;
    public static final int NUMBER_MIN =1;
    public static final int NUMBER_MAX =45;

    public static void main(String[] args) {
        String message = "총 몇게임을 하시겠습니까?";
        int gameSize = ScannerUtil.nextInt(SCANNER, message);

        int[][] userNumbers = new int[gameSize][ARRAY_LENGTH];

        setNumbers(userNumbers);

        int[] computerNumbers = new int[ARRAY_LENGTH];
        Ex10Lotto03Re.setAutoNumbers(computerNumbers);
        Ex10Lotto03Re.sort(computerNumbers);

        printResult(userNumbers,computerNumbers);
    }

    private static void setNumbers(int[][] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.println((i + 1) + "번째 게임");
            String message = "1.자동 2.수동";
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 2);

            if (userChoice == 1) {
                Ex10Lotto03Re.setAutoNumbers(arrays[i]);
            }else{
                Ex10Lotto03Re.setManualNumbers(arrays[i]);
            }

            Ex10Lotto03Re.sort(arrays[i]);
        }
    }

    public static void printResult(int[][] arrays, int[] array) {
        System.out.print("컴퓨터 숫자: ");
        printArray(array);
        System.out.println();
        System.out.print("사용자 숫자\n");
        for (int i = 0; i < arrays.length; i++) {
            System.out.printf("%d번 게임",i + 1);
            printArray(arrays[i]);
            System.out.printf(" - %d개\n",countSame(array,arrays[i]));
        }
    }

    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%2d", array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
    private static int countSame(int[] computerNumbers,int[] userNumbers) {
        int count =0;
        for (int i = 0; i < computerNumbers.length; i++) {
            if (Ex10Lotto03Re.contains(userNumbers, computerNumbers[i])) {
                count++;
            }
        }
        return count;
    }
}
