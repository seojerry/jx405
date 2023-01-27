package javabasic.miniproject.viewer;

import javabasic.miniproject.controller.ScreenInfoController;
import javabasic.miniproject.controller.TheaterController;
import javabasic.miniproject.model.Movie;
import javabasic.miniproject.model.ScreenInfo;
import javabasic.miniproject.model.Theater;
import javabasic.miniproject.model.User;
import javabasic.util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ScreenInfoViewer {
    //    private BoardController boardController;
//    private javabasic.viewer.UserViewer userViewer;
    private ScreenInfoController screenInfoController;
    private javabasic.miniproject.viewer.UserViewer userViewer;
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;
    private final Scanner SCANNER;
    private User logIn;
    private ArrayList<Movie> movieArrayList;
    private ArrayList<Theater> theaterArrayList;

    public ScreenInfoViewer(Scanner scanner) {
        screenInfoController = new ScreenInfoController();
        SCANNER = scanner;
    }

    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }
    public void setMovieViewer(MovieViewer movieViewer) {
        this.movieViewer = movieViewer;
    }
    public void setTheaterViewer(TheaterViewer theaterViewer) {
        this.theaterViewer = theaterViewer;
    }


    public void setLogIn(User logIn) {
        this.logIn = logIn;
    }

    public void showMenu() {
            String message = "1. 상영 정보 목록 보기  2.등록  3. 종료";
            while (true) {
                int userChoice = ScannerUtil.nextInt(SCANNER, message,1,3);
                if (userChoice == 1) {
                    printList();
                }else if (userChoice == 2) {
                  registerScreenInfo();
                } else if (userChoice == 3) {
                    System.out.println("사용해주셔서 감사합니다.");
                    if (logIn.getGrade()==0){
                        userViewer.showMenuA();
                    } else if (logIn.getGrade() == 1) {
                        userViewer.showMenuN();
                    } else if (logIn.getGrade() == 2) {
                        userViewer.showMenuR();
                    }

                }
            }
        }

    public void printAllInfo() {
        movieArrayList = movieViewer.getList();
        theaterArrayList = theaterViewer.getList();
        for (Movie m : movieArrayList) {
            System.out.printf("영화번호:%4d  영화이름:%10s \n",m.getMovieId(),m.getTitle());
        }
        for (Theater t : theaterArrayList) {
            System.out.printf("극장번호:%4d  극장이름:%10s \n",t.getTheaterId(),t.getName());
        }
    }
    private void registerScreenInfo() {
        //정보를 받을수 있게 영화와 극장의 리스트들을 번호와 함께 띄워주는것이 필요
        printAllInfo();
        ScreenInfo screenInfo = new ScreenInfo();

        String message = "영화 번호를 입력해주세요.";
        while (true) {
            int flag = 0;
            int num = ScannerUtil.nextInt(SCANNER, message);
            for (Movie m : movieViewer.getList()) {
                if (num == m.getMovieId()) {
                    flag = 1;
                }
            }
            if (flag == 1) {
                screenInfo.setMovieId(num);
                break;
            } else {
                System.out.println("해당 영화가 없습니다.");
            }
        }



        message = "극장 번호를 입력해주세요.";

        while (true) {
            int flag = 0;
            int num = ScannerUtil.nextInt(SCANNER, message);
            for (Theater t : theaterViewer.getList()) {
                if (num == t.getTheaterId()) {
                    flag = 1;
                }
            }
                if (flag == 1) {
                    screenInfo.setTheaterId(num);
                    break;
                } else {
                    System.out.println("해당 극장이 없습니다. 다시 입력하세요.");
                }
            }


            message = "상영시간을 입력해주세요.";
            screenInfo.setRunningTime(ScannerUtil.nextLine(SCANNER, message));

            screenInfoController.add(screenInfo);
        }


    private void printList() {
        ArrayList<ScreenInfo> list = screenInfoController.selectAll();
        if (list.isEmpty()) {
            System.out.println("아직 등록된 상영정보가 없습니다.");
        } else {
            for (ScreenInfo b : list) {
                System.out.printf("번호:%d  영화이름:%s  극장이름:%s  상영시간:%s  \n",b.getScreenInpoId(), movieViewer.selectOne(b.getMovieId()).getTitle(), theaterViewer.selectOne(b.getTheaterId()).getName(), b.getRunningTime());
            }

            showMenu2();
        }
    }

    public void showMenu2() {
        if (screenInfoController.selectAll().size() == 0) {
            System.out.println("등록된 정보가 없습니다.");
            return;
        }
        while (true) {
            String message = "1. 수정  2. 삭제  3. 뒤로 가기 ";
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
            if (userChoice == 1) {
                message = "번호를 입려해주세요.";
                int id = ScannerUtil.nextInt(SCANNER, message);
                if (screenInfoController.selectOne(id) == null) {
                    System.out.println("해당 상영정보가 없습니다.");
                    continue;
                }
                update(id);
            } else if (userChoice == 2) {
                message = "번호를 입려해주세요.";
                int id = ScannerUtil.nextInt(SCANNER, message);
                if (screenInfoController.selectOne(id) == null) {
                    System.out.println("해당 상영정보가 없습니다.");
                    continue;
                }
                delete(id);
            } else if (userChoice == 3) {
                showMenu();
            }
        }
    }

    public ArrayList<ScreenInfo> getList() {
        return screenInfoController.selectAll();
    }

    private void update(int id) {
        ScreenInfo b = screenInfoController.selectOne(id);
        while (true) {
            String message = "새로운 영화 번호를 입력해주세요.";
            int flag = 0;
            int num = ScannerUtil.nextInt(SCANNER, message);
            for (ScreenInfo s : screenInfoController.selectAll()) {
                if (s.getMovieId() == num) {
                    flag = 1;
                }
            }
            if (flag==0) {
                System.out.println("해당 영화가 없습니다.");
                continue;
            }

            b.setMovieId(num);
            break;
//            for (Movie m : movieViewer.getList()) {
//                if (num == m.getMovieId()) {
//                    flag = 1;
//                }
//            }
//            if (flag == 1) {
//                b.setMovieId(num);
//                break;
//            } else {
//                System.out.println("해당 영화가 없습니다.");
//            }
        }
        while (true) {
            String message = "새로운 극장 번호를 입력해주세요.";
            int flag = 0;
            int num = ScannerUtil.nextInt(SCANNER, message);
            for (Theater t : theaterViewer.getList()) {
                if (num == t.getTheaterId()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("해당 극장이 없습니다.");
                continue;
            }
            b.setTheaterId(num);
            break;

        }

        String message = "새로운 상영시간을 입력해주세요.";
        b.setRunningTime(ScannerUtil.nextLine(SCANNER, message));

        screenInfoController.update(b);
        printList();
    }

    private void delete(int id) {

        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            screenInfoController.delete(id);
//            showMenu2();
            printList();
        } else {
//            showMenu2();
            printList();
        }
    }
}
