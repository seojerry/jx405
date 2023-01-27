package javabasic.miniproject.controller;

import javabasic.miniproject.model.Theater;
import javabasic.miniproject.model.User;
import javabasic.model.BoardDTO;

import java.util.ArrayList;
import java.util.Date;

public class TheaterController {
    private ArrayList<Theater> list;
    private int nextId;

    public TheaterController() {
        list = new ArrayList<>();
        nextId = 1;


        Theater t = new Theater();
        t.setName("용산 CGV");
        t.setPosition("용산역");
        t.setTel("02-111-1111");
        add(t);

    }

    public void add(Theater theater) {
        theater.setTheaterId(nextId++);
        list.add(theater);
    }

    public Theater selectOne(int id) {
        Theater temp = new Theater(id);
        if (list.contains(temp)) {
            return new Theater(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<Theater> selectAll() {
        ArrayList<Theater> temp = new ArrayList<>();
        for (Theater b : list) {
            temp.add(new Theater(b));
        }

        return temp;
    }

    public void update(Theater theater) {
        list.set(list.indexOf(theater), theater);
    }



    public void delete(int id) {
        list.remove(new Theater(id));
    }
}

