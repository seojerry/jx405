package javabasic.miniproject;

//import javabasic.viewer.BoardViewer;
//import javabasic.viewer.ReplyViewer;
//import javabasic.viewer.UserViewer;

import javabasic.miniproject.model.Movie;
import javabasic.miniproject.viewer.*;

import java.util.Scanner;

public class Ex01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        BoardViewer boardViewer = new BoardViewer(scanner);
        UserViewer userViewer = new UserViewer(scanner);
//        ReplyViewer replyViewer = new ReplyViewer(scanner);
        MovieViewer movieViewer = new MovieViewer(scanner);
        ReviewViewer reviewViewer = new ReviewViewer(scanner);
        TheaterViewer theaterViewer = new TheaterViewer(scanner);
        ScreenInfoViewer screenInfoViewer = new ScreenInfoViewer(scanner);

//        userViewer.setBoardViewer(boardViewer);
//        userViewer.setReplyViewer(replyViewer);
//        boardViewer.setUserViewer(userViewer);
//        boardViewer.setReplyViewer(replyViewer);
        movieViewer.setReviewViewer(reviewViewer);
        userViewer.setMovieViewer(movieViewer);
        userViewer.setReviewViewer(reviewViewer);
        userViewer.setTheaterViewer(theaterViewer);
        userViewer.setScreenInfoViewer(screenInfoViewer);
        screenInfoViewer.setMovieViewer(movieViewer);
        screenInfoViewer.setTheaterViewer(theaterViewer);
        theaterViewer.setScreenInfoViewer(screenInfoViewer);
        theaterViewer.setMovieViewer(movieViewer);
        userViewer.showIndex();
    }
}

