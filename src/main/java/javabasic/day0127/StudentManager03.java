package javabasic.day0127;

import javabasic.day0127.viewer.StudentViewer;
import javabasic.dbConn.ConnectionMaker;
import javabasic.dbConn.MySqlConnectionMaker;

public class StudentManager03 {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        StudentViewer studentViewer = new StudentViewer(connectionMaker);
        studentViewer.showMenu();
    }
}
