package javabasic.miniproject.model;

import java.util.Date;

public class Review {
    private int reviewId;
    private int userId;
    private int movieId;
    private int grade;
    private String comment;
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

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean equals(Object o) {
        if (o instanceof Review) {
            Review b = (Review) o;
            return reviewId == b.reviewId;
        }
        return false;
    }

    public Review(Review origin) {
        reviewId = origin.reviewId;
        userId = origin.userId;
        movieId = origin.movieId;
        grade  = origin.grade;
        comment = origin.comment;
    }

    public Review() {

    }

    public Review(int id) {
        this.reviewId = id;
    }
}
