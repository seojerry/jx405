package javabasic.day0126.mvc.viewer;

import javabasic.controller.UserController;
import javabasic.day0126.mvc.model.StudentDTO;
import javabasic.day0126.mvc.controller.StudentController;
import javabasic.util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;



public class StudentViewer {
    private final Scanner SCANNER;
    private PreparedStatement pstmt = null;
    private Connection connection = null;
    private ResultSet resultSet = null;
    private StudentController studentController;

    public StudentViewer(Scanner scanner) {
        SCANNER = scanner;
        studentController = new StudentController();
        initialize();
    }

    private void initialize() {
        String address = "jdbc:mysql://localhost/basic";
        String username = "root";
        String password = "1111";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(address, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMenu() {
        String message = "1.입력 2.목록 보기 3.종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                insert();
            } else if (userChoice == 2) {
                printList();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                try {
                    studentController.terminate(resultSet,pstmt,connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                SCANNER.close();
                break;
            }
        }
    }

    public void printList() {
        String query = "SELECT * FROM student";

        try {
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery(query);

            ArrayList<StudentDTO> list = studentController.selectAll(resultSet);

            if (list.isEmpty()) {
                System.out.println("아직 등록된 학생이 존재하지 않습니다.");
            } else {
                for (StudentDTO s : list) {
                    System.out.printf("%d. %s\n",s.getId(),s.getName());
                }

                String message = "상세보기할 학생의 번호나 뒤로 가실려면 0을 입력해주세요.";
                int userChoice = ScannerUtil.nextInt(SCANNER, message);

                while (userChoice != 0 && studentController.selectOne(userChoice,resultSet,pstmt,connection) == null) {
                    System.out.println("잘못 입력하셨습니다.");
                    userChoice = ScannerUtil.nextInt(SCANNER, message);
                }

                if (userChoice != 0) {
                    printOne(userChoice);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void printOne(int id) {
        StudentDTO s = studentController.selectOne(id,resultSet,pstmt,connection);
        s.printInfo();

        String message = "1.수정 2.삭제 3.뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        if (userChoice == 1) {
            update(id);
        } else if (userChoice == 2) {
            delete(id);
        } else if (userChoice == 3) {
            printList();
        }
    }
    private void insert() {
        StudentDTO s = new StudentDTO();

        String message = "학생의 이름을 입력해주세요.";
        s.setName(ScannerUtil.nextLine(SCANNER, message));
        message = "학생의 국어 점수를 입력해주세요.";
        s.setKorean(ScannerUtil.nextInt(SCANNER,message));
        message = "학생의 영어 점수를 입력해주세요.";
        s.setEnglish(ScannerUtil.nextInt(SCANNER,message));
        message = "학생의 수학 점수를 입력해주세요.";
        s.setMath(ScannerUtil.nextInt(SCANNER,message));
        String query = "INSERT INTO student(name,korean,english,math) VALUES(?,?,?,?)";

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, s.getName());
            pstmt.setInt(2, s.getKorean());
            pstmt.setInt(3, s.getEnglish());
            pstmt.setInt(4, s.getMath());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(int id) {
        String message = "새로운 국어 점수를 입력해주세요.";
        int korean = ScannerUtil.nextInt(SCANNER, message, 0, 100);
        message = "새로운 영어 점수를 입력해주세요.";
        int english = ScannerUtil.nextInt(SCANNER, message, 0, 100);
        message = "새로운 수학 점수를 입력해주세요.";
        int math = ScannerUtil.nextInt(SCANNER, message, 0, 100);

        String query = "UPDATE student SET korean =?,english = ? , math=? WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,korean);
            pstmt.setInt(2,english);
            pstmt.setInt(3,math);
            pstmt.setInt(4,id);

            pstmt.executeUpdate();

            printOne(id); //실행 끝난뒤 다시 실행
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(int id) {
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            String query = "DELETE FROM student WHERE id=?";
            try {
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            printOne(id);
        }
    }
}
