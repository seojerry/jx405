package javabasic.miniproject.controller;

import javabasic.miniproject.model.Review;
import javabasic.model.ReplyDTO;

import java.util.ArrayList;
import java.util.Date;

public class ReviewController {
    private ArrayList<Review> list;
    private int nextId;

    public ReviewController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void add(Review review) {
        review.setReviewId(nextId++);
        review.setEntryDate(new Date());
        list.add(review);
    }

    public ArrayList<Review> selectAll(int movieId) {
        ArrayList<Review> temp = new ArrayList<>();
        for (Review r : list) {
            if (r.getMovieId() == movieId) {
                temp.add(new Review(r));
            }
        }

        return temp;
    }

    public Review selectOne(int id) {
        Review r = new Review();
        r.setReviewId(id);
        if (list.contains(r)) {
            return new Review(list.get(list.indexOf(r)));

        } else {
            return null;
        }
    }

    public void update(Review review) {
        review.setModifyDate(new Date());
        list.set(list.indexOf(review), review);
    }

    public void delete(int id) {
        Review r = new Review();
        r.setReviewId(id);
        list.remove(r);
    }
}