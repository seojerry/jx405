package javabasic.videomanager.controller;


import javabasic.videomanager.model.RentalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class RentalController {
    private Connection connection;

    public RentalController(Connection connection) {
        this.connection = connection;
    }

    public void insert(RentalDTO rentalDTO) {
        String query = "INSERT INTO rental (rental_id,rental_date,inventory_id,customer_id,return_date,staff_id,last_update) VALUES(?,NOW(),?,?,null,?,NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, rentalDTO.getRental_id());
            pstmt.setInt(2, rentalDTO.getInventory_id());
            pstmt.setInt(3, rentalDTO.getCustomer_id());
            pstmt.setInt(4, rentalDTO.getStaff_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RentalDTO> selectAll() {
        ArrayList<RentalDTO> list = new ArrayList<>();

        String query = "SELECT * FROM rental ORDER BY rental_id DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                RentalDTO r = new RentalDTO();
                r.setRental_id(resultSet.getInt("rental_id"));
                r.setRental_date(resultSet.getDate("rental_date"));
                r.setInventory_id(resultSet.getInt("inventory_id"));
                r.setCustomer_id(resultSet.getInt("customer_id"));
                r.setReturn_date(resultSet.getDate("return_date"));
                r.setStaff_id(resultSet.getInt("staff_id"));
                r.setLast_update(resultSet.getDate("last_update"));

//                Date entry_date = resultSet.getTimestamp("entry_date");
                list.add(r);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public RentalDTO selectOne(int id) {
        RentalDTO rentalDTO = null;
        String query = "SELECT * FROM rental WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                rentalDTO = new RentalDTO();
                rentalDTO.setRental_id(resultSet.getInt("rental_id"));
                rentalDTO.setRental_date(resultSet.getDate("rental_date"));
                rentalDTO.setInventory_id(resultSet.getInt("inventory_id"));
                rentalDTO.setCustomer_id(resultSet.getInt("customer_id"));
                rentalDTO.setReturn_date(resultSet.getDate("return_date"));
                rentalDTO.setStaff_id(resultSet.getInt("staff_id"));
                rentalDTO.setLast_update(resultSet.getDate("last_update"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalDTO;
    }

    public void update(RentalDTO rentalDTO) {
        String query = "UPDATE rental SET rental_date =? , inventory_id =? ,customer_id = ?,return_date=NOW(),staff_id=?,last_update=NOW() WHERE rental_id =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDate(1, rentalDTO.getRental_date());
            pstmt.setInt(2,rentalDTO.getInventory_id());
            pstmt.setInt(3, rentalDTO.getCustomer_id());
            pstmt.setInt(4, rentalDTO.getStaff_id());
            pstmt.setInt(5, rentalDTO.getRental_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM rental WHERE id =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,id);

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
