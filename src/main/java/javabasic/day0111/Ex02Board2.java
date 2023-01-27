package javabasic.day0111;

import javabasic.util.ArrayUtil;
import javabasic.util.ScannerUtil;

import javax.script.ScriptContext;
import java.util.Scanner;

public class Ex02Board2 {
    public static final Scanner SCANNER = new Scanner(System.in);

//    public static Board[] boardArray = new Board[5];
    public static Board[] boardArray = new Board[0];

    public static int nextId = 1;
    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            String message = "1.입력 2.출력 3.삭제 4.종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                writeBoard();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                removeBoard();
            } else if (userChoice == 4) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private static void removeBoard() {
        String message = "삭제할 id를 입력해주세요.";
        int id = ScannerUtil.nextInt(SCANNER, message);
        boardArray=ArrayUtil.removeById(boardArray,id);
    }

    public static void printList() {
//        if (findEmptyIndex() == 0) {
        if (boardArray.length == 0) {
            System.out.println("아직 작성된 게시글이 없습니다.");
        }else {
//            for (int i = 0; i < findEmptyIndex(); i++) {
            for (int i = 0; i < boardArray.length; i++) {
                System.out.println(boardArray[i].getId() + ". " + boardArray[i].getTitle());
            }

            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while ((userChoice != 0) && findIndexById(userChoice) == -1) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    public static void printOne(int id) {
        if (findIndexById(id) == -1) {
            System.out.println("해당 id를 가진 게시글은 존재하지 않습니다.");
        }else {
            boardArray[findIndexById(id)].printBoard();
            String message = "1.수정 2.뒤로가기";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                update(id);
            } else {
                printList();
            }
        }
    }

    public static void update(int id) {
        int index = findIndexById(id);
        boardArray[index].setTitle(ScannerUtil.nextLine(SCANNER, "새로운 제목을 입력해주세요"));

        boardArray[index].setContent(ScannerUtil.nextLine(SCANNER, "새로운 내용을 입력해주세요."));

        printOne(id);

    }

    private static int findIndexById(int id) {
        for (int i = 0; i < boardArray.length; i++) {
            if (boardArray[i] != null && id == boardArray[i].getId()) {
                return i;
            }
        }
        return -1;
    }

    public static void writeBoard() {
        Board temp = new Board();
//        int nextIndex = findEmptyIndex();
//        if (nextIndex >= boardArray.length) {
//            System.out.println("더이상 작성하실 수 없습니다.");
//        } else {
//            Board b = new Board();
            String message;

            temp.setId(nextId++);
            message = "글의 작성자를 입력해주세요.";
            temp.setWriter(ScannerUtil.nextLine(SCANNER, message));

            message = "글의 제목를 입력해주세요.";
            temp.setTitle(ScannerUtil.nextLine(SCANNER, message));
            message = "글의 내용를 입력해주세요.";
            temp.setContent(ScannerUtil.nextLine(SCANNER, message));
        boardArray=ArrayUtil.add(boardArray, temp);
//            boardArray[nextIndex] = b;
//        }
    }

    public static int findEmptyIndex() {
        for (int i = 0; i < boardArray.length; i++) {
            if (boardArray[i] == null) {
                return i;
            }
        }

        return boardArray.length;
    }
}