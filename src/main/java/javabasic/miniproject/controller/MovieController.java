package javabasic.miniproject.controller;

import javabasic.miniproject.model.Movie;
import javabasic.miniproject.model.Theater;


import java.util.ArrayList;
import java.util.Date;

public class MovieController {
    private ArrayList<Movie> list;
    private int nextId;

    public MovieController() {
        list = new ArrayList<>();
        nextId = 1;

        Movie m = new Movie();
        m.setTitle("더 퍼스트 슬램덩크");
        m.setGrade("전체 관람가");
        m.setContent("전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 영화");
        m.setEntryDate(new Date());
        m.setModifyDate(new Date());
        add(m);
    }

    public void add(Movie movie) {
        movie.setMovieId(nextId++);
        movie.setEntryDate(new Date());
        movie.setModifyDate(new Date());
        list.add(movie);
    }

    public Movie selectOne(int id) {
        Movie temp = new Movie(id);
        if (list.contains(temp)) {
            return new Movie(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<Movie> selectAll() {
        ArrayList<Movie> temp = new ArrayList<>();
        for (Movie b : list) {
            temp.add(new Movie(b));
        }

        return temp;
    }

    public void update(Movie movie) {
        movie.setModifyDate(new Date());
        list.set(list.indexOf(movie), movie);
    }


    public void delete(int id) {
        list.remove(new Movie(id));
    }
}
