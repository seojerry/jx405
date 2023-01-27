package javabasic.miniproject.viewer;

//import javabasic.controller.UserController;
import javabasic.miniproject.controller.UserController;
//import javabasic.model.UserDTO;
import javabasic.miniproject.model.Movie;
import javabasic.miniproject.model.ScreenInfo;
import javabasic.miniproject.model.User;
import javabasic.model.BoardDTO;
import javabasic.util.ScannerUtil;
import javabasic.miniproject.viewer.MovieViewer;
//import javabasic.viewer.BoardViewer;
//import javabasic.viewer.ReplyViewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class UserViewer {
    private final Scanner SCANNER;
    private UserController userController;
    //    private BoardViewer boardViewer;
//    private ReplyViewer replyViewer;
    private MovieViewer movieViewer;
    private ReviewViewer reviewViewer;
    private TheaterViewer theaterViewer;
    private ScreenInfoViewer screenInfoViewer;
    private User logIn = null;

    public UserViewer(Scanner scanner) {
        SCANNER = scanner;
        userController = new UserController();
    }

    public void setScreenInfoViewer(ScreenInfoViewer screenInfoViewer) {
        this.screenInfoViewer = screenInfoViewer;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }
    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }

    //    public void setBoardViewer(BoardViewer boardViewer) {
//        this.boardViewer = boardViewer;
//    }
    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }
//    public void setReplyViewer(ReplyViewer replyViewer) {
//        this.replyViewer = replyViewer;
//    }

    public void showIndex() {
        String message = "1. 로그인 2. 회원가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message,1,3);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    movieViewer.setLogIn(logIn);
                    reviewViewer.setLogIn(logIn);
                    theaterViewer.setLogIn(logIn);
                    screenInfoViewer.setLogIn(logIn);
//                    screenInfoViewer.setLogIn(logIn);
//                    boardViewer.setLogIn(logIn);
//                    replyViewer.setLogIn(logIn);

                    switch (logIn.getGrade()) {
                        case 0:
                            showMenuA();
                            break;
                        case 1:
                            showMenuN();
                            break;
                        case 2:
                            showMenuR();
                            break;
                    }
                }
            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void register() {
        String message;
        message = "사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        while (!userController.validateUsername(username)) {
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = "사용하실 아이디나 뒤로 가실려면 \"X\"를 입력해주세요.";
            username = ScannerUtil.nextLine(SCANNER, message);

            if (username.equalsIgnoreCase("X")) {
                break;
            }
        }

        if (!username.equalsIgnoreCase("X")) {
            User u = new User();
            u.setUsername(username);

            if (u.getUsername().equals("admin")){
                u.setGrade(0);
            }else {
                u.setGrade(1);
            }

            message = "사용하실 비밀번호를 입력해주세요.";
            u.setPassword(ScannerUtil.nextLine(SCANNER, message));

            message = "사용하실 닉네임을 입력해주세요.";
            u.setNickname(ScannerUtil.nextLine(SCANNER, message));

            userController.insert(u);
        }
    }

    private void auth() {
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        logIn = userController.auth(username, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }

    public void showMenuA() {
        String message = "1. 영화 목록 2. 극장 목록 3.상영 정보 4. 회원 정보 관리 5. 등급 변경 6. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message,1,6);
            if (userChoice == 1) {
//                boardViewer.showMenu();
                movieViewer.showMenu();
            }else if (userChoice == 2) {
                theaterViewer.showMenu();
            }
            else if (userChoice == 3) {
                screenInfoViewer.showMenu();
            }
            else if (userChoice == 4) {
                printOne();
            } else if (userChoice == 5) {
                printList();
            } else if (userChoice == 6) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }
    public void showMenuN() {
        String message = "1. 영화 목록 2. 극장 목록 3. 회원 정보 관리 4. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message,1,4);
            if (userChoice == 1) {
//                boardViewer.showMenu();
                movieViewer.showMenu();
            } else if (userChoice == 2) {
                theaterViewer.showMenu();
            } else if (userChoice == 3) {
                printOne();
            } else if (userChoice == 4) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    public void showMenuR() {
        String message = "1. 영화 목록 2. 극장 목록 3. 회원 정보 관리 4. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message,1,4);
            if (userChoice == 1) {
//                boardViewer.showMenu();
                movieViewer.showMenu();
            }else if (userChoice == 2) {
                theaterViewer.showMenu();
            }
            else if (userChoice == 3) {
                printOne();
            } else if (userChoice == 4) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    private void printOne() {
        System.out.println("회원 번호: " + logIn.getUserId());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("회원 등급: " + logIn.getGrade());
        System.out.println("---------------------------------------------");
        String message = "1. 수정 2. 탈퇴 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        }
    }

    private void update() {

        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (logIn.getPassword().equals(oldPassword)) {
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            userController.update(logIn);
        } else {
            System.out.println("회원 정보 변경에 실패하였습니다.");
        }
    }

    private void delete() {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER, message);

            if (password.equals(logIn.getPassword())) {
                userController.delete(logIn.getUserId());
                logIn = null;
            }
        }
    }
    private void printList() {
        ArrayList<User> list = userController.selectAll();

//        if (list.isEmpty()) {
//            System.out.println("아직 등록된 글이 없습니다.");
//        } else {
//            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            for (User b : list) {
                System.out.printf("%d. %s \n", b.getUserId(), b.getUsername());
            }

            String message = "바꾸실 회원의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new User(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printUser(userChoice);
            }
        }
//    }
    private void printUser(int id) {
        User user = userController.selectOne(id);
        System.out.println("-------------------------------------------------");
        System.out.println("회원 번호: " +user.getUserId() );
        System.out.println("회원 아이디: " + user.getUsername());
        System.out.println("회원 닉네임: "+ user.getNickname());
        System.out.println("회원 등급: "+ user.getGrade());
        System.out.println("-------------------------------------------------");

        String message = "회원의 등급을 설정해주세요.\n 0)관리자 1)일반 관람객 2)전문 평론가 ";
        int userChoice = ScannerUtil.nextInt(SCANNER,message,0,2);
        user.setGrade(userChoice);
        userController.update(user);
        System.out.println("변경되었습니다.");

    }
}

