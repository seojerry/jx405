package javabasic.miniproject.viewer;

import javabasic.controller.ReplyController;
import javabasic.miniproject.controller.ReviewController;
import javabasic.miniproject.model.Review;
import javabasic.model.ReplyDTO;
//import javabasic.model.UserDTO;
import javabasic.miniproject.model.User;
import javabasic.util.ScannerUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewViewer {
    private Scanner SCANNER;
    private ReviewController reviewController;

    private User logIn;

    public ReviewViewer(Scanner scanner) {
        this.SCANNER = scanner;
        reviewController = new ReviewController();
    }
    public double avgReview(int movieId){
        ArrayList<Review> temp= reviewController.selectAll(movieId);
        int sum = 0;
        for (Review r : temp) {
            sum += r.getGrade();
            }
        if (temp.size() == 0) {
            return 0;
        }else{
            return sum/temp.size()*1.0;
        }

        }



    public void setLogIn(User logIn) {
        this.logIn = logIn;
    }
    public void printAll(int movieId) {
        ArrayList<Review> list = reviewController.selectAll(movieId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMd H:m:s");
        for (Review r : list) {
            String date = sdf.format(r.getEntryDate());
            if (r.getModifyDate() != null) {
                date = sdf.format(r.getModifyDate());
            }
            System.out.printf("%d. %s(%s): %s\n", r.getReviewId(), r.getUserId(), date,r.getGrade());
        }
    }

    public void showMenu(int movieId) {
        while (true) {
            String message = "1.평점 등록  2. 평점 수정 3. 평점 삭제  4. 일반 관람객 평점  5. 평론가 평점  6. 뒤로 가기";
            int userChoice = ScannerUtil.nextInt(SCANNER, message,1,6);
            if (userChoice == 1) {
//            writeReply(boardId);
                registerReview(movieId);
//        } else if (userChoice != 3) {
//            message = "수정/삭제할 댓글 번호나 뒤로 가실려면 0을 입력해주세요.";
//            int targetId = ScannerUtil.nextInt(SCANNER, message);
//            while (userChoice != 0 && reviewController.selectOne(targetId) == null) {
//                System.out.println("잘못 입력하셨습니다.");
//                targetId = ScannerUtil.nextInt(SCANNER, message);
//            }
            }else if (userChoice == 2) {
                updateReview(movieId);
            } else if (userChoice == 3) {
                deleteReview(movieId);
            } else if (userChoice == 4) {
                showPublicReview(movieId);
            } else if (userChoice == 5) {
                showCriticReview(movieId);
            } else if (userChoice == 6) {
                break;
            }
        }

        }

    public void showPublicReview(int movieId) {
        ArrayList<Review> temp = reviewController.selectAll(movieId);
        for (Review r : temp) {
            if (r != null && r.getMovieId() == movieId && (r.getUserId()==1 || r.getUserId()==0)) {
                System.out.printf("%s: %d \n",r.getUserId(),r.getGrade());
            }
        }
    }
    public void showCriticReview(int movieId) {
        ArrayList<Review> temp = reviewController.selectAll(movieId);
        for (Review r : temp) {
            if (r != null && r.getMovieId() == movieId && r.getUserId()==2) {
                System.out.printf("%s: %d \n",r.getUserId(),r.getGrade());
            }
        }
    }
    public void registerReview(int movieId) {
        Review r = new Review();
        r.setMovieId(movieId);
        r.setUserId(logIn.getUserId());

        String message = "평점을 입력해주세요.(1~5)";
        r.setGrade(ScannerUtil.nextInt(SCANNER,message,1,5));

        reviewController.add(r);
    }


    public void deleteReview(int movieId) {
        ArrayList<Review> temp = reviewController.selectAll(movieId);
        for (Review r : temp) {
            if (r != null && r.getMovieId() == movieId && r.getUserId() == logIn.getUserId()) {
                String message = "정말로 삭제하시겠습니까? Y/N";
                String yesNo = ScannerUtil.nextLine(SCANNER, message);
                if (yesNo.equalsIgnoreCase("Y")) {
                    reviewController.delete(r.getReviewId());
                }
            }
        }
    }

    private void updateReview( int movieId) {
        ArrayList<Review> temp = reviewController.selectAll(movieId);
        for (Review r : temp) {
            if (r != null && r.getMovieId() == movieId && r.getUserId() == logIn.getUserId()) {
                String message = "새로운 평점을 입력해주세요.";
                r.setGrade(ScannerUtil.nextInt(SCANNER, message,1,5));
                reviewController.update(r);
            }
        }
    }
}
