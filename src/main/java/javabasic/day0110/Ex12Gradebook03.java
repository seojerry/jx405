package javabasic.day0110;

//1. 5명의 학생의 성적을 관리하는 프로그램을 작성하시오.
//  단,5명을 모두 입력한 후에는 더이상 입력할수 없도록 코드를 작성하시오.
//2. 5명의 학생 성적을 관리하는 프로그램을 작성하시오.
//  단, 5명을 모두 입력한 후에 새로운 학생 정보를 입력할 시에는
//  가장 오래된 기록을 제거하고 새로운 학생 정보를 입력되도록 코드를 작성하시오.

import javabasic.util.ScannerUtil;

import java.util.Scanner;




public class Ex12Gradebook03 {
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

            //2.출력
            printInfo(studentNum[i],students[i],kor[i],eng[i],math[i]);

        }
    }
    public static void printInfo(int id, String name, int korean, int english, int math) {
        System.out.println("번호: "+id+"번 이름: "+name);
        System.out.printf("국어: %d점 영어: %d점 수학:%d점\n", korean, english, math);
        System.out.printf("총점: %d점 평균: %f점\n",calculateSum(korean,english,math),calculateAverage(korean,english,math));
    }
    public static int getId(Scanner scanner) {
        String message = "학생의 번호를 입력해주세요.";
        return ScannerUtil.nextInt(scanner,message);
    }

    public static String getName(Scanner scanner) {
        String temp;
        String message;
        message = "학생의 이름을 입력해주세요.";
        temp = ScannerUtil.nextLine(scanner,message);
        return temp;
    }
    public static int getKorean(Scanner scanner){
        int temp;
        String message;
        message = "학생의 국어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
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
    public static int getEnglish(Scanner scanner){
        int temp;
        String message;
        message = "학생의 영어 점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
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
        System.out.println(message);
        System.out.println("> ");
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
