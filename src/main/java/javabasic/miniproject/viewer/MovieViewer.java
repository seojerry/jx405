package javabasic.miniproject.viewer;
//import javabasic.controller.BoardController;
import javabasic.miniproject.controller.MovieController;
//import javabasic.model.BoardDTO;
//import javabasic.model.UserDTO;
import javabasic.miniproject.model.Movie;
import javabasic.miniproject.model.Theater;
import javabasic.miniproject.model.User;
import javabasic.util.ScannerUtil;
//import javabasic.viewer.ReplyViewer;
//import javabasic.viewer.UserViewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    //    private BoardController boardController;
//    private javabasic.viewer.UserViewer userViewer;
    private MovieController movieController;
    private UserViewer userViewer;
    private ReviewViewer reviewViewer;


//    private ReplyViewer replyViewer;
    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private User logIn;

    public MovieViewer(Scanner scanner) {
        movieController = new MovieController();
        SCANNER = scanner;
    }

    public void setReviewViewer(ReviewViewer reviewViewer) {
        this.reviewViewer = reviewViewer;
    }
    public void setUserViewer(UserViewer userViewer) {
        this.userViewer = userViewer;
    }

//    public void setReplyViewer(ReplyViewer replyViewer) {
//        this.replyViewer = replyViewer;
//    }

    public void setLogIn(User logIn) {
        this.logIn = logIn;
    }
    public Movie selectOne(int id) {
        return  movieController.selectOne(id);
    }
    public void showMenu() {
        if (logIn.getGrade() == 0) {
            String message = "1. 영화 목록 보기 2. 영화 등록  3. 종료";
            while (true) {
                int userChoice = ScannerUtil.nextInt(SCANNER, message,1,3);
                if (userChoice == 1) {
//                writeBoard();
                    printList();
                } else if (userChoice == 2) {
                    registerMovie();
                } else if (userChoice == 3) {
                    System.out.println("사용해주셔서 감사합니다.");
                    break;
                }
            }
        }else{
            String message = "1. 영화 목록 보기 2. 종료 ";
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

    private void registerMovie() {
        Movie movie = new Movie();
//        movie.setWriterId(logIn.getId());
//        movie.setWriterNickname(logIn.getNickname());

        String message = "영화의 제목을 입력해주세요.";
        movie.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "글의 내용을 입력해주세요.";
        movie.setContent(ScannerUtil.nextLine(SCANNER, message));

        message = "영화 등급을 입력해주세요.\n1.전체 관람가 2.15세 관람가 3.19세 관람가";
        int userChoice =ScannerUtil.nextInt(SCANNER, message,1,3);
        if (userChoice == 1) {
            movie.setGrade("전체 관람가");
        } else if (userChoice == 2) {
            movie.setGrade("15세 관람가");
        } else if (userChoice == 3) {
            movie.setGrade("19세 관람가");
        }

        movieController.add(movie);
    }

    private void printList() {
        ArrayList<Movie> list = movieController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 영화가 없습니다.");
        } else {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            for (Movie b : list) {
                System.out.printf("%d. %s (%s) - %s\n", b.getMovieId(), b.getTitle(),reviewViewer.avgReview(b.getMovieId()), df.format(b.getEntryDate()));
            }

            String message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new Movie(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        Movie movie = movieController.selectOne(id);
        System.out.println("=================================================");
        System.out.println(movie.getTitle());
        System.out.println("-------------------------------------------------");
        System.out.println("줄거리: \n");
        System.out.println(movie.getContent());
        System.out.println("작성일: " + df.format(movie.getEntryDate()));
        System.out.println("수정일: " + df.format(movie.getModifyDate()));
        System.out.println("-------------------------------------------------");
//        replyViewer.printAll(id);
        System.out.println(movie.getGrade());
        System.out.println("=================================================");
        String message;
        int userChoice;

        if (logIn.getGrade() == 0) {
            message = "1.평점  2. 수정 3. 삭제 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 4);
            if (userChoice == 1) {
                reviewViewer.showMenu(movie.getMovieId());
            } else if (userChoice == 2) {
                update(id);
            } else if (userChoice == 3) {
                delete(id);
            } else if (userChoice == 4) {
                printList();
            }
        } else if (logIn.getGrade()==1) {
            message = "1. 평점  2. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 2);
            if (userChoice == 1) {
                reviewViewer.showMenu(movie.getMovieId());
            } else if (userChoice == 2) {
                printList();
            }

        } else if (logIn.getGrade() == 2) {
            message = "1. 평점  2. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 1);
            if (userChoice == 1) {
                reviewViewer.showMenu(movie.getMovieId());
            } else if (userChoice == 2) {
                printList();
            }
        }
    }

    private void update(int id) {
        Movie b = movieController.selectOne(id);

        String message = "새로운 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 내용을 입력해주세요.";
        b.setContent(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 영화 등급을 입력해주세요.\n1.전체 관람가 2.15세 관람가 3.19세 관람가";
        int userChoice =ScannerUtil.nextInt(SCANNER, message,1,3);
        if (userChoice == 1) {
            b.setGrade("전체 관람가");
        } else if (userChoice == 2) {
            b.setGrade("15세 관람가");
        } else if (userChoice == 3) {
            b.setGrade("19세 관람가");
        }


        movieController.update(b);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            movieController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
    public ArrayList<Movie> getList() {
        return movieController.selectAll();
    }
}