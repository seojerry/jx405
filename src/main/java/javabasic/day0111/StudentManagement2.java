package javabasic.day0111;


import javabasic.util.ArrayUtil;
import javabasic.util.ScannerUtil;

import java.util.Scanner;

public class StudentManagement2 {
    public static final int STUDENT_SIZE = 5;
//    public static Student[] studentArray = new Student[STUDENT_SIZE];
    public static Student[] studentArray = new Student[0];
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            int caseNum=showMenu();
            switch (caseNum) {
                case 1:
                    register(studentArray);
                    break;
                case 2:
                    showInfo(studentArray);
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    exit();
            }
        }
    }

    private static void removeStudent() {
        String message = "삭제할 학생번호를 입력하세요.";
        int id = ScannerUtil.nextInt(SCANNER, message);
        int index = findIndexById(id);
        studentArray = ArrayUtil.removeByIndex(studentArray, index);
    }

    private static void showInfo(Student[] s) {
        for (int i = 0; i < studentArray.length; i++) {
            if(s[i]==null) return;
            System.out.printf("학생번호:%d 이름:%s 국어:%d 영어:%d 수학:%d 총점:%d 평균:%.2f\n",
                    s[i].getId(),s[i].getName(),s[i].getKorean(),s[i].getMath(),s[i].getEnglish(),s[i].calculateSum(),s[i].calculateAverage());
        }
    }

    private static void exit() {
        System.exit(0);
    }

    private static void register(Student[] s) {
//        int index = findIndex(s);
//        if (index == -1) {
//            moveElement();
//            index = STUDENT_SIZE-1;
//        }

        Student temp = new Student();
        System.out.print("학생의 번호를 입력해주세요. > ");
        temp.setId(SCANNER.nextInt());
        System.out.print("학생의 이름을 입력해주세요. > ");
        temp.setName(SCANNER.next());
        System.out.print("학생의 국어점수를 입력해주세요. > ");
        temp.setKorean(SCANNER.nextInt());
        System.out.print("학생의 영어점수를 입력해주세요. > ");
        temp.setEnglish(SCANNER.nextInt());
        System.out.print("학생의 수학점수를 입력해주세요. > ");
        temp.setMath(SCANNER.nextInt());
        studentArray= ArrayUtil.add(studentArray, temp);
    }

    private static void moveElement() {
        for (int i = 0; i < STUDENT_SIZE-1; i++) {
            studentArray[i] = studentArray[i + 1];
        }
    }

    private static int findIndex(Student[] s) {
        for (int i = 0; i < STUDENT_SIZE; i++) {
            if (s[i]==null) return i;
        }
        return -1;
    }
    private static int findIndexById(int id) {
        for (int i = 0; i < studentArray.length; i++) {
            if (id == studentArray[i].getId()) {
                return i;
            }
        }
        return -1;
    }
    private static int showMenu() {
        System.out.println("1.등록 2.출력 3.삭제 4.종료");
        System.out.print(">");
        int result =SCANNER.nextInt();
        return result;
    }
}
