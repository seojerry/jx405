package javabasic.miniprojectreview;

import javabasic.miniprojectreview.viewer.FilmViewer;
import javabasic.miniprojectreview.viewer.MemberViewer;
import javabasic.miniprojectreview.viewer.ReviewViewer;

import java.util.Scanner;

public class MovieProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //각 뷰어 초기화
        MemberViewer memberViewer = new MemberViewer(scanner);
        FilmViewer filmViewer = new FilmViewer(scanner);
        ReviewViewer reviewViewer = new ReviewViewer(scanner);

        //의존성 주입
        memberViewer.setFilmViewer(filmViewer);
        memberViewer.setReviewViewer(reviewViewer);

        filmViewer.setReviewViewer(reviewViewer);

        reviewViewer.setMemberViewer(memberViewer);

        memberViewer.showIndex();
    }
}
