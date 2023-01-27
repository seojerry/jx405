package javabasic.day0111;
//접근제한자-public,private,protected,default
//public-다른 패키지까지 사용가능
//protected-같은 패키지에 있는 외부 클래스 사용가능,외부 패키지의 경우 상속관계가 아니면 사용가능x
//private-해당 클래스 내부에서만 사용가능
//default-같은 패키지에 있는 외부 클래스만 사용가능 (패키지 접근 제한자)


import java.util.Date;
import java.util.Scanner;

public class Board {
    private int id;
    private String title;
    private String writer;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void printBoard() {
        System.out.println("--------------------------------");
        System.out.println(title);
        System.out.println("--------------------------------");
        System.out.println("번호: " + id);
        System.out.println("작성자: " + writer);
        System.out.println("--------------------------------");
        System.out.println(content);
    }

    public boolean equals(Object o) {
        if (o instanceof Board) {
            Board b = (Board) o;
            return id == b.id;
        }
        return false;
    }
}
