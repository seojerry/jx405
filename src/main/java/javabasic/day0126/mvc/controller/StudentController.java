package javabasic.day0126.mvc.controller;

import javabasic.day0126.Student;
import javabasic.day0126.mvc.model.StudentDTO;
import javabasic.model.UserDTO;
import javabasic.util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;

public class StudentController {


    public void terminate(ResultSet resultSet,PreparedStatement pstmt,Connection connection) throws  SQLException{
        if (resultSet != null) {
            resultSet.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }

        if (connection != null) {
            connection.close();
        }
//        SCANNER.close();
    }
    //insert()




    public ArrayList<StudentDTO> selectAll(ResultSet resultSet) {
        ArrayList<StudentDTO> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                StudentDTO s = new StudentDTO();
                s.setId((resultSet.getInt("id")));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));
                s.setName(resultSet.getString("name"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public StudentDTO selectOne(int id,ResultSet resultSet,PreparedStatement pstmt,Connection connection) {
        String query = "SELECT * FROM student WHERE id =?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                StudentDTO s = new StudentDTO();
                s.setId(resultSet.getInt("id"));
                s.setName(resultSet.getString("name"));
                s.setKorean(resultSet.getInt("korean"));
                s.setEnglish(resultSet.getInt("english"));
                s.setMath(resultSet.getInt("math"));

                return s;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
