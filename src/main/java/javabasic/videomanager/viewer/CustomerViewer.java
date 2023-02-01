package javabasic.videomanager.viewer;

import javabasic.util.ScannerUtil;
import javabasic.videomanager.controller.CustomerController;
import javabasic.videomanager.model.CustomerDTO;
import javabasic.videomanager.model.StaffDTO;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerViewer {
    private CustomerController customerController;
    private Connection connection;


    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private StaffDTO logIn;

    public CustomerViewer(Scanner scanner, Connection connection, StaffDTO logIn) {
        this.connection = connection;
        customerController = new CustomerController(this.connection);
        this.logIn = logIn;
        SCANNER = scanner;
    }



    public void showMenu() {
        String message = "1. 고객 리스트 출력 2. 검색(수정,삭제) 3. 뒤로 가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                printList();
            } else if (userChoice == 2) {
                searchCustomer();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void searchCustomer() {
        ArrayList<CustomerDTO> list = new ArrayList<>();

        String message = "고객의 first_name을 입력해주세요.";
        String first_name = ScannerUtil.nextLine(SCANNER, message);
        message = "고객의 last_name을 입력해주세요.";
        String last_name = ScannerUtil.nextLine(SCANNER, message);
        list =customerController.SearchByName(first_name,last_name);

        if (list.isEmpty()) {
            System.out.println("아직 등록된 고객이 없습니다.");
        } else {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            for (CustomerDTO c : list) {
                System.out.printf("%d. %s %s %s - %s\n", c.getCustomer_id(), c.getFirst_name(), c.getLast_name(), c.getEmail(), df.format(c.getCreate_date()));
            }
            message = "상세보기할 글의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            while (userChoice != 0 && !list.contains(new CustomerDTO(userChoice))) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printList() {
        ArrayList<CustomerDTO> list = customerController.selectAll();

        if (list.isEmpty()) {
            System.out.println("아직 등록된 고객이 없습니다.");
        } else {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            for (CustomerDTO c : list) {
                System.out.printf("%d. %s %s %s - %s\n", c.getCustomer_id(), c.getFirst_name(),c.getLast_name(),c.getEmail(), df.format(c.getCreate_date()));
            }

        }
    }

    private void printOne(int id) {
        CustomerController customerController = new CustomerController(connection);

        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        CustomerDTO customerDTO = customerController.selectOne(id);
        System.out.println("=================================================");
        System.out.println("-------------------------------------------------");
        System.out.println("고객 번호: " + customerDTO.getCustomer_id());
        System.out.println("First Name: " + customerDTO.getFirst_name());
        System.out.println("Last Name: "+customerDTO.getLast_name());
        System.out.println("email: "+customerDTO.getEmail());
        System.out.println("등록일: "+customerDTO.getCreate_date());
        System.out.println("-------------------------------------------------");
        System.out.println("=================================================");
        String message;
        int userChoice;
        message = "1. 수정 2. 삭제  3. 뒤로 가기";
        userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);

        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        }
    }

    private void update(int id) {
        CustomerDTO c = customerController.selectOne(id);

        String message = "새로운 First Name을 입력해주세요.";
        c.setFirst_name(ScannerUtil.nextLine(SCANNER, message));

        message = "새로운 Last Name을 입력해주세요.";
        c.setLast_name(ScannerUtil.nextLine(SCANNER, message));


        message = "새로운 email을 입력해주세요.";
        c.setEmail(ScannerUtil.nextLine(SCANNER, message));

        customerController.update(c);
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);
        if (yesNo.equalsIgnoreCase("Y")) {
            customerController.delete(id);
            printList();
        } else {
            printOne(id);
        }
    }
}
