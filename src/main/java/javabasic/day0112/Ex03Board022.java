package javabasic.day0112;

import javabasic.viewer.*;

import java.util.Scanner;

public class Ex03Board022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BoardViewer boardViewer = new BoardViewer(scanner);
        UserViewer userViewer = new UserViewer(scanner);
        CommentViewer commentViewer = new CommentViewer((scanner));

        userViewer.setBoardViewer(boardViewer);
        boardViewer.setUserViewer(userViewer);


        userViewer.showIndex();
    }
}
