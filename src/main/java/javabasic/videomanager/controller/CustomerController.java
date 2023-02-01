package javabasic.videomanager.controller;

import javabasic.videomanager.model.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    private Connection connection;

    public CustomerController(Connection connection) {
        this.connection = connection;
    }

    public void insert(CustomerDTO customerDTO) {
        String query = "INSERT INTO customer (store_id,first_name,last_name,email,create_date,last_update) VALUES(?,?,?,?,NOW(),NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, customerDTO.getStore_id());
            pstmt.setString(2, customerDTO.getFirst_name());
            pstmt.setString(3, customerDTO.getLast_name());
            pstmt.setString(4, customerDTO.getEmail());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CustomerDTO> selectAll() {
        ArrayList<CustomerDTO> list = new ArrayList<>();

        String query = "SELECT * FROM customer ORDER BY customer_id DESC";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setCustomer_id(resultSet.getInt("customer_id"));
                c.setStore_id(resultSet.getInt("store_id"));
                c.setFirst_name(resultSet.getString("first_name"));
                c.setLast_name(resultSet.getString("last_name"));
                c.setEmail(resultSet.getString("email"));
                c.setAddress_id(resultSet.getInt("address_id"));
                c.setActive(resultSet.getInt("active"));
                c.setCreate_date(resultSet.getDate("create_date"));
                c.setLast_update(resultSet.getTimestamp("last_update"));

//                Date entry_date = resultSet.getTimestamp("entry_date");
                list.add(c);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<CustomerDTO> SearchByName(String first_name,String last_name) {
        ArrayList<CustomerDTO> list = new ArrayList<>();
        String query = "SELECT * FROM customer Where first_name = ? and last_name = ? ORDER BY customer_id DESC ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,first_name);
            pstmt.setString(2,last_name);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setCustomer_id(resultSet.getInt("customer_id"));
                c.setStore_id(resultSet.getInt("store_id"));
                c.setFirst_name(resultSet.getString("first_name"));
                c.setLast_name(resultSet.getString("last_name"));
                c.setEmail(resultSet.getString("email"));
                c.setAddress_id(resultSet.getInt("address_id"));
                c.setActive(resultSet.getInt("active"));
                c.setCreate_date(resultSet.getDate("create_date"));
                c.setLast_update(resultSet.getTimestamp("last_update"));

//                Date entry_date = resultSet.getTimestamp("entry_date");
                list.add(c);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public CustomerDTO selectOne(int id) {
        CustomerDTO customerDTO = null;
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                customerDTO = new CustomerDTO();
                customerDTO.setCustomer_id(resultSet.getInt("customer_id"));
                customerDTO.setStore_id(resultSet.getInt("store_id"));
                customerDTO.setFirst_name(resultSet.getString("first_name"));
                customerDTO.setLast_name(resultSet.getString("last_name"));
                customerDTO.setEmail(resultSet.getString("email"));
                customerDTO.setAddress_id(resultSet.getInt("address_id"));
                customerDTO.setActive(resultSet.getInt("active"));
                customerDTO.setCreate_date(resultSet.getDate("create_date"));
                customerDTO.setLast_update(resultSet.getTimestamp("last_update"));

            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    public CustomerDTO selectOneByEmail(String email) {
        CustomerDTO customerDTO = null;
        String query = "SELECT * FROM customer WHERE email = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                customerDTO = new CustomerDTO();
                customerDTO.setCustomer_id(resultSet.getInt("customer_id"));
                customerDTO.setStore_id(resultSet.getInt("store_id"));
                customerDTO.setFirst_name(resultSet.getString("first_name"));
                customerDTO.setLast_name(resultSet.getString("last_name"));
                customerDTO.setEmail(resultSet.getString("email"));
                customerDTO.setAddress_id(resultSet.getInt("address_id"));
                customerDTO.setActive(resultSet.getInt("active"));
                customerDTO.setCreate_date(resultSet.getDate("create_date"));
                customerDTO.setLast_update(resultSet.getTimestamp("last_update"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTO;
    }

    public void update(CustomerDTO customerDTO) {
        String query = "UPDATE board SET first_name =? , last_name =? ,email=?,last_update = NOW() WHERE id =?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, customerDTO.getFirst_name());
            pstmt.setString(2,customerDTO.getLast_name());
            pstmt.setString(3, customerDTO.getEmail());
            pstmt.setInt(4, customerDTO.getCustomer_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        String query = "DELETE FROM customer WHERE id =?";
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
