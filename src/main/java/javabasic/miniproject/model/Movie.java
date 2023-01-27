package javabasic.miniproject.model;

import java.util.Date;

public class Movie {
    private int movieId;
    private String title;
    private String content;
    private String grade;
    private Date entryDate;
    private Date modifyDate;

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String  getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public boolean equals(Object o) {
        if (o instanceof Movie) {
            Movie b = (Movie) o;
            return movieId == b.movieId;
        }
        return false;
    }

    public Movie(Movie origin) {
        movieId = origin.movieId;
        title  = origin.title;
        content = origin.content;
        grade = origin.grade;
        entryDate = origin.entryDate;
        modifyDate = origin.modifyDate;
    }

    public Movie() {

    }

    public Movie(int id) {
        this.movieId = id;
    }
}
