package javabasic.miniproject.viewer;

import javabasic.controller.BoardController;
import javabasic.miniproject.controller.MovieController;
import javabasic.miniproject.controller.TheaterController;
import javabasic.miniproject.model.Movie;
import javabasic.miniproject.model.ScreenInfo;
import javabasic.miniproject.model.Theater;
import javabasic.miniproject.model.User;
import javabasic.model.BoardDTO;

import javabasic.util.ScannerUtil;
import javabasic.viewer.ReplyViewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TheaterViewer {
    //    private BoardController boardController;
//    private javabasic.viewer.UserViewer userViewer;
    private TheaterController theaterController;
    private javabasic.miniproject.viewer.UserViewer userViewer;
    private ScreenInfoViewer screenInfoViewer;
    private MovieViewer movieViewer;
    private final Scanner SCANNER;
    private User logIn;

    public TheaterViewer(Scanner scanner) {
        theaterController = new TheaterController();
        SCANNER = scanner;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }
    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    public void setScreenInfoViewer(ScreenInfoViewer screenInfoViewer) {
        this.screenInfoViewer = screenInfoViewer;
    }


    public void setLogIn(User logIn) {
        this.logIn = logIn;
    }

    public void showMenu() {
        if (logIn.getGrade() == 0) {
            String message = "1. 극장 목록 보기 2. 극장 등록  3. 종료";
            while (true) {
                int userChoice = ScannerUtil.nextInt(SCANNER, message,1,3);
                if (userChoice == 1) {
//                writeBoard();
                    printList();
                } else if (userChoice == 2) {
                    registerTheater();
                } else if (userChoice == 3) {
                    System.out.println("사용해주셔서 감사합니다.");
                    break;
                }
            }
        }else{
            String message = "1. 극장 목록 보기 2. 종료 ";
            while (true) {
                int userChoice = ScannerUtil.nextInt(SCANNER, message,1,2);
                if (userChoice == 1) {
//                writeBoard();
                    printList();
                } else if (userChoice == 2) {
                    System.out.println("사용해주셔서 감사합니다.");
                    break;
                }
            }
        }
    }

    public Theater selectOne(int id) {
        return  theaterController.selectOne(id);
    }

    private void registerTheater() {
//        Movie movie = new Movie();
        Theater theater = new Theater();
//        movie.setWriterId(logIn.getId());
//        movie.setWriterNickname(logIn.getNickname());

        String message = "극장 이름을 입력해주세요.";
        theater.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 위치를 입력해주세요.";
        theater.setPosition(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 전화번호를 입력해주세요.";
        theater.setTel(ScannerUtil.nextLine(SCANNER, message));

        theaterController.add(theater);
    }

    private void printList() {
        ArrayList<Theater> list = theaterController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 극장이 없습니다.");
        } else {
            for (Theater b : list) {
                System.out.printf("%d. %s  %s  %s\n", b.getTheaterId(), b.getName(),b.getPosition(),b.getTel());
            }

            String message = "상세보기할 극장의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new Theater(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        Theater theater = theaterController.selectOne(id);
        System.out.println("=================================================");
        System.out.println(theater.getTheaterId() + ". " + theater.getName());
        System.out.println("-------------------------------------------------");
        System.out.println("movie list \n"); // 상영정보에서 해당 극장과 동일한 아이디의 영화 리스트 출력
        for (ScreenInfo s :screenInfoViewer.getList()){
            if (s.getTheaterId() == id) {
                for (Movie m:movieViewer.getList()){
                    if(s.getMovieId()==m.getMovieId())
                        System.out.println(m.getTitle());
                }
            }
        }
        System.out.println("-------------------------------------------------");
//        replyViewer.printAll(id);
        System.out.println("Location:" + theater.getPosition() + " tel:" + theater.getTel());
        System.out.println("=================================================");
        String message;
        int userChoice;

        if (logIn.getGrade() == 0) {
            message = "1. 수정  2. 삭제 3. 뒤로 가기 ";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                update(id);
            } else if (userChoice == 2) {
                delete(id);
            } else if (userChoice == 3) {
                printList();
            } else {
                message = "1. 뒤로 가기";
                userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 1);
                if (userChoice == 1) {
                    printList();
                }
            }
        }
    }

    private void update(int id) {
        Theater b = theaterController.selectOne(id);

        String message = "새로운 이름을 입력해주세요.";
        b.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 위치를 입력해주세요.";
        b.setPosition(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 전화번호를 입력해주세요.";
        b.setTel(ScannerUtil.nextLine(SCANNER, message));

        theaterController.update(b);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            theaterController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }

    public ArrayList<Theater> getList() {
        return theaterController.selectAll();
    }
}