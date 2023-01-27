package javabasic.day0112;

import javabasic.viewer.BoardViewer;

import javabasic.viewer.ReplyViewer;
import javabasic.viewer.UserViewer;

import java.util.Scanner;

public class Ex03Board02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BoardViewer boardViewer = new BoardViewer(scanner);
        UserViewer userViewer = new UserViewer(scanner);
        ReplyViewer replyViewer = new ReplyViewer(scanner);

        userViewer.setBoardViewer(boardViewer);
        userViewer.setReplyViewer(replyViewer);
        boardViewer.setUserViewer(userViewer);
        boardViewer.setReplyViewer(replyViewer);
        userViewer.showIndex();
    }
}
