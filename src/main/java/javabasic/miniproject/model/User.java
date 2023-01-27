package javabasic.miniproject.model;

import javabasic.model.BoardDTO;

public class User {
    private int userId;
    private String username;
    private String  password;
    private String nickname;
    private int grade;  //0:관리자 1:일반 회원 2:전문 평론가

    public int getUserId() {
        return userId;
    }

    public void setUserId(int memberid) {
        this.userId = memberid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean equals(Object o) {
        if (o instanceof User) {
            User b = (User) o;
            return userId == b.userId;
        }
        return false;
    }

    public User(User origin) {
        userId = origin.userId;
        username = origin.username;
        password = origin.password;
        nickname = origin.nickname;
        grade = origin.grade;
    }

    public User() {

    }

    public User(int id) {
        this.userId = id;
    }
}
