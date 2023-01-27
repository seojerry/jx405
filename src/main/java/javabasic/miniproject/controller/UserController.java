package javabasic.miniproject.controller;

import javabasic.miniproject.model.User;
import javabasic.model.BoardDTO;

import java.util.ArrayList;
import java.util.Date;

public class UserController {
    private ArrayList<User> list;
    private int nextId;

    public UserController() {
        list = new ArrayList<>();
        nextId = 1;

        for (int i = 0; i <=2; i++) {
            User b= new User();
            b.setUsername("user"+i);
            b.setNickname("user"+i);
            b.setPassword("user"+i);
            b.setGrade(i);
            add(b);
        }
    }
        public void add(User user) {
            user.setUserId(nextId++);
            list.add(user);
        }

    public void insert(User user) {
        user.setUserId(nextId++);
        list.add(user);
    }

    public User selectById(int id) {
        for (User u : list) {
            if (u.getUserId() == id) {
                return new User(u);
            }
        }

        return null;
    }

    public User selectOne(int id) {
        User temp = new User(id);
        if (list.contains(temp)) {
            return new User(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<User> selectAll() {
        ArrayList<User> temp = new ArrayList<>();
        for (User b : list) {
            temp.add(new User(b));
        }

        return temp;
    }

    public void update(User user) {
        list.set(list.indexOf(user), user);
    }

    public void delete(int id) {
        User u = new User();
        u.setUserId(id);
        list.remove(u);
    }

    public boolean validateUsername(String username) {
        if (username.equalsIgnoreCase("X")) {
            return false;
        }

        for (User u : list) {
            if (username.equalsIgnoreCase(u.getUsername())) {
                return false;
            }
        }

        return true;
    }

    public User auth(String username, String password) {
        for (User u : list) {
            if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {
                return new User(u);
            }
        }

        return null;
    }
}