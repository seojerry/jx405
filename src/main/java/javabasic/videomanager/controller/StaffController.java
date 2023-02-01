package javabasic.videomanager.controller;



import javabasic.videomanager.model.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffController {
    private Connection connection;

    public StaffController(Connection connection) {
        this.connection = connection;
    }

    public boolean insert(StaffDTO staffDTO) {
        String query = "INSERT INTO `staff`(`first_name`, `last_name`,`username`,`password`,`last_update`) VALUES(?,?,?,?,NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, staffDTO.getFirst_name());
            pstmt.setString(2, staffDTO.getLast_name());
            pstmt.setString(3, staffDTO.getUsername());
            pstmt.setString(4, staffDTO.getPassword());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    public StaffDTO auth(String username, String password) {
        String query = "SELECT * FROM `staff` WHERE `username` = ? AND `password` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                StaffDTO staffDTO = new StaffDTO();
                staffDTO.setStaff_id(resultSet.getInt("staff_id"));
                staffDTO.setUsername(resultSet.getString("username"));
                staffDTO.setFirst_name(resultSet.getString("first_name"));
                staffDTO.setLast_name(resultSet.getString("last_name"));
                staffDTO.setStore_id(resultSet.getInt("store_id"));

                return staffDTO;
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(StaffDTO staffDTO) {
        String query = "UPDATE `staff` SET `password` = ?, `first_name`=?,`last_name` = ? WHERE `staff_id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, staffDTO.getPassword());
            pstmt.setString(2, staffDTO.getFirst_name());
            pstmt.setString(3, staffDTO.getLast_name());
            pstmt.setInt(4, staffDTO.getStaff_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE  FROM `staff` WHERE `staff_id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StaffDTO selectOne(int id) {
        StaffDTO u = null;
        String query = "SELECT * FROM staff WHERE id =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                u = new StaffDTO();
                u.setStaff_id(resultSet.getInt("staff_id"));
                u.setUsername(resultSet.getString("username"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<StaffDTO> selectAll() {
        ArrayList<StaffDTO> list = new ArrayList<>();

        String query = "SELECT * FROM staff ORDER BY staff.staff_id DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                StaffDTO s = new StaffDTO();
                s.setStaff_id(resultSet.getInt("staff_id"));
                s.setFirst_name(resultSet.getString("first_name"));
                s.setLast_name(resultSet.getString("last_name"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setPicture(resultSet.getBlob("picture"));
                s.setEmail(resultSet.getString("email"));
                s.setStore_id(resultSet.getInt("store_id"));
                s.setActive(resultSet.getInt("active"));
                s.setUsername(resultSet.getString("username"));
                s.setPassword(resultSet.getString("password"));
                s.setLast_update(resultSet.getTimestamp("last_update"));
                list.add(s);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
