package javabasic.day0113;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Ex04Date {
    public abstract int showNmae();
    public static void main(String[] args) {
        Date d = new Date();
        d.setHours(16);
        d.setMonth(1); //월은 0~11
        System.out.println(d);


        DateFormat df = new SimpleDateFormat("yMd E h:m:s");
        // y: 연도
        // M: 월
        // d: 해당 월의 일자
        // E: 요일


        //h: 오전/오후 체계의 현재 시간
        //H: 24시간 체계의 현재 시간
        //m: 현재 분
        //s: 현재 초
        //S: 현재 밀리초


        System.out.println(df.format(d));
        //yyMMdd hh:mm:ss

        df = new SimpleDateFormat("yyMMdd");
        df.setLenient(false);
        String temp = "230193"; //형식이 맞지 않을경우 에러발생
        try {
            d = df.parse(temp);
        } catch (Exception e) {
            System.out.println("해당 스트링은 date로 변환할수 없어서 현재 날짜로 date를 설정하겠습니다.");
            d = new Date();
        }
        System.out.println(d);
    }

}
