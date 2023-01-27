package javabasic.viewer;

import javabasic.controller.BoardController;
import javabasic.controller.CommentController;
import javabasic.model.BoardDTO;
import javabasic.model.CommentDTO;
import javabasic.util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CommentViewer {
    private CommentController commentController;
    private BoardViewer boardViewer;
    private final Scanner SCANNER;

    public CommentViewer(Scanner scanner) {
        commentController = new CommentController();
        SCANNER = scanner;
    }
    public void setBoardViewer(BoardViewer boardViewer) {
        this.boardViewer = boardViewer;
    }
    public void createComment(int id,String writer) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setWriter(writer);
        commentController.add(commentDTO);
    }

    public void printComment() {

    }

    public void getComment(int id) {
        CommentDTO commentDTO = commentController.selectOne(id);
        String message= "댓글을 입력해주세요.";
        commentDTO.setComment(ScannerUtil.nextLine(SCANNER,message));
    }
}
