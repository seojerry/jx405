package javabasic.day0109;
//사용자로붜 번호,이름,국어,영어,수학 점수를 입력 받아서
// 각각의 정보를 다음과 같이 출력되는 프로그램을 작성하시오.
//단, 입력에 관한 메소드, 출력에 관한 메소드, 총점 및 평균을 계산 하는 메소드를 따로 분리하시오.

import java.util.Scanner;

//출력 방법:
// 번호: ###번 이름:###
//국어: ##점 영어: ##점 수학: ##점
//총점: ##점 평균:##.#####점
public class Ex02Gradebook {
    public static void main(String[] args) {
        int number=0;
        String name = "";
        int kor=0;
        int eng=0;
        int math=0;
        input(number,name,kor,eng,math);

    }

public static void input(int number ,String name, int kor ,int eng,int math){
    Scanner sc = new Scanner(System.in);
    System.out.print("번호:");
    number = sc.nextInt();
    System.out.print("이름:");
    name = sc.next();
    System.out.print("국어:");
    kor = sc.nextInt();
    System.out.print("영어:");
    eng = sc.nextInt();
    System.out.print("수학:");
    math = sc.nextInt();
    output(number,name,kor,eng,math,total(kor,eng,math),avg(kor,eng,math));
   }
  public static void output(int number ,String name, int kor ,int eng,int math,int total,double avg){
      System.out.println("번호: " + number + "번 이름:" + name);
      System.out.println("국어: " + kor + "점 영어: " + eng + "점 수학:" + math + "점");
      System.out.println("총점: "+total+"점 평균: "+avg+"점");
  }

    public static int total(int a,int b,int c) {
        int result = a+b+c;
        return result;
    }
  public  static  double avg(int a,int b,int c){
      double result = (a + b + c) / 3.0;
      return result;
  }
}
