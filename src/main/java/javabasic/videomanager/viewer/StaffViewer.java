package javabasic.videomanager.viewer;


import javabasic.dbConn.ConnectionMaker;
import javabasic.util.ScannerUtil;
import javabasic.videomanager.controller.StaffController;
import javabasic.videomanager.model.StaffDTO;

import java.sql.Connection;
import java.util.Scanner;

public class StaffViewer {
    private final Scanner SCANNER;
    private Connection connection;
    private StaffDTO logIn;


    public StaffViewer(ConnectionMaker connectionMaker) {
        SCANNER = new Scanner(System.in);
        connection = connectionMaker.makeConnection();
    }

    public void showIndex() {
        String message = "1. 로그인 2. 회원 가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }
            } else if (userChoice == 2) {
                register();
            }else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void register() {
        String message;
        message = "사용하실 아이디를 입력해주세요.";

        StaffDTO s = new StaffDTO();
        s.setUsername(ScannerUtil.nextLine(SCANNER, message));

        message = "사용하실 비밀번호를 입력해주세요.";
        s.setPassword(ScannerUtil.nextLine(SCANNER, message));

        message = "first name을 입력해주세요.";
        s.setFirst_name(ScannerUtil.nextLine(SCANNER, message));

        message = "last name을 입력해주세요.";
        s.setLast_name(ScannerUtil.nextLine(SCANNER, message));



        StaffController staffController = new StaffController(connection);
        if (!staffController.insert(s)) {
            System.out.println("중복된 아이디입니다.");
            message = "새로운 아이디로 가입을 시도하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {
                register();
            }
        }

    }

    private void auth() {
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        StaffController staffController = new StaffController(connection);

        logIn = staffController.auth(username, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }

    private void showMenu() {
        String message = "1. 대여 등록  2.반납  3. 고객 정보 관리 4. 대여 정보 보기 5. 회원 정보 보기 6. 로그아웃";
        while (logIn != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                RentalViewer rentalViewer = new RentalViewer(SCANNER, connection, logIn);
                rentalViewer.function();

            } else if (userChoice == 2) {
                RentalViewer rentalViewer = new RentalViewer(SCANNER,connection,logIn);
                rentalViewer.returnVideo();
            }else if (userChoice == 3) {
                CustomerViewer customerViewer = new CustomerViewer(SCANNER, connection, logIn);
                customerViewer.showMenu();
            }else if (userChoice == 4) {
                RentalViewer rentalViewer = new RentalViewer(SCANNER, connection, logIn);
                rentalViewer.printList();
            }else if (userChoice == 5) {
               printOne();
            } else if (userChoice == 6) {
                logIn = null;
                System.out.println("정상적으로 로그아웃되었습니다.");
            }
        }
    }

    private void printOne() {
        System.out.println("회원 번호: " + logIn.getStaff_id());
        System.out.println("회원 아이디: " + logIn.getUsername());
        System.out.println("First name: " + logIn.getFirst_name());
        System.out.println("Last name: " + logIn.getLast_name());
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



        message = "새로운 first_name을 입력해주세요.";
        String first_name = ScannerUtil.nextLine(SCANNER, message);

        message = "새로운 last_name을 입력해주세요.";
        String last_name = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        StaffController staffController = new StaffController(connection);

        if (staffController.auth(logIn.getUsername(), oldPassword) != null) {
            logIn.setFirst_name(first_name);
            logIn.setLast_name(first_name);
            logIn.setPassword(newPassword);
            staffController.update(logIn);
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

            StaffController staffController = new StaffController(connection);

            if (staffController.auth(logIn.getUsername(), password) != null) {
                staffController.delete(logIn.getStaff_id());
                logIn = null;
            }
        }
    }
}
