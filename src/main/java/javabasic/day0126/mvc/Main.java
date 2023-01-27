package javabasic.day0126.mvc;

import javabasic.day0126.mvc.viewer.StudentViewer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentViewer studentViewer = new StudentViewer(scanner);
        studentViewer.showMenu();
    }
}
