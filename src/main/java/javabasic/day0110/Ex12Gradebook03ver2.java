package javabasic.day0110;
//2. 5명의 학생 성적을 관리하는 프로그램을 작성하시오.
//  단, 5명을 모두 입력한 후에 새로운 학생 정보를 입력할 시에는
//  가장 오래된 기록을 제거하고 새로운 학생 정보를 입력되도록 코드를 작성하시오.

import javabasic.util.ScannerUtil;

import java.util.Scanner;

public class Ex12Gradebook03ver2 {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int STUDENT_NUMBER=5;
    public static void main(String[] args) {
        String[] students = new String[STUDENT_NUMBER];
        int[] studentNum = new int[STUDENT_NUMBER];
        int[] kor = new int[STUDENT_NUMBER];
        int[] eng = new int[STUDENT_NUMBER];
        int[] math = new int[STUDENT_NUMBER];
        int[] total = new int[STUDENT_NUMBER];
        Double[] avg = new Double[STUDENT_NUMBER];
        for (int i = 0; i < STUDENT_NUMBER; i++) {
            //입력
            //1-1. 번호 입력
            studentNum[i] = getId(SCANNER);
            //1-2. 이름 입력
            students[i] = getName(SCANNER);
            //1-3. 국어 점수 입력
            kor[i] = getKorean(SCANNER);
            //1-4. 영어 점수 입력
            eng[i] = getEnglish(SCANNER);
            //1-5. 수학 점수 입력
            math[i] = getMath(SCANNER);
        }
        while (true) {
            System.out.print("계속 입력을 원하시면 1번 출력을 원하시면 2번 > ");
            int choice = SCANNER.nextInt();
            switch (choice) {
                case 1:
                    resort(studentNum,students,kor,eng,math);
                    studentNum[STUDENT_NUMBER-1] = getId(SCANNER);
                    students[STUDENT_NUMBER-1] = getName(SCANNER);
                    kor[STUDENT_NUMBER-1] = getKorean(SCANNER);
                    eng[STUDENT_NUMBER-1] = getEnglish(SCANNER);
                    math[STUDENT_NUMBER-1] = getMath(SCANNER);
                    break;
                case 2:
                    //2.출력
                    printInfo(studentNum,students,kor,eng,math);
                    break;
                default:
                    System.out.println("잘못된 입력값으로 시스템을 종료합니다.");
                    System.exit(0);
            }
        }
    }

    private static void resort(int[] studentNum, String[] students, int[] kor, int[] eng, int[] math) {
        for (int i = 1; i < STUDENT_NUMBER; i++) {
            studentNum[i - 1] = studentNum[i];
            students[i - 1] = students[i];
            kor[i - 1] = kor[i];
            eng[i - 1] = eng[i];
            math[i - 1] = math[i];
        }
    }

    public static void printInfo(int[] id, String[] name, int[] korean, int[] english, int[] math) {
        for (int i = 0; i < STUDENT_NUMBER; i++) {
            System.out.print("번호: " + id[i] + "번 이름: " + name[i]);
            System.out.printf(" 국어: %d점 영어: %d점 수학:%d점", korean[i], english[i], math[i]);
            System.out.printf(" 총점: %d점 평균: %f점\n", calculateSum(korean[i], english[i], math[i]), calculateAverage(korean[i], english[i], math[i]));
        }
    }
    public static int getId(Scanner scanner) {
        System.out.print("학생의 번호를 입력해주세요. > ");
        return scanner.nextInt();
    }

    public static String getName(Scanner scanner) {
        String temp;
        System.out.print("학생의 이름을 입력해주세요. > ");
        scanner.nextLine();
        temp = scanner.nextLine();
        return temp;
    }
    public static int getKorean(Scanner scanner){
        int temp;
        String message;
        message = "학생의 국어 점수를 입력해주세요.";
        System.out.print(message);
        System.out.print(" > ");
        temp = scanner.nextInt();

        int min;
        int max;
        min=0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.print(message);
            System.out.print(" > ");
            temp = scanner.nextInt();
        }
        return temp;
    }
    public static int getEnglish(Scanner scanner){
        int temp;
        String message;
        message = "학생의 영어 점수를 입력해주세요.";
        System.out.print(message);
        System.out.print(" > ");
        temp = scanner.nextInt();

        int min;
        int max;
        min=0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }
        return temp;
    }
    public static int getMath(Scanner scanner){
        int temp;
        String message;
        message = "학생의 수학 점수를 입력해주세요.";
        System.out.print(message);
        System.out.print(" > ");
        temp = scanner.nextInt();

        int min;
        int max;
        min=0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }
        return temp;
    }
    public static int calculateSum(int korean, int english, int math) {
        return korean + english + math;
    }

    public static double calculateAverage(int korean, int english, int math) {
        final int SUBJECT_SIZE = 3;
        return calculateSum(korean,english,math) / (double)SUBJECT_SIZE;
    }
}


