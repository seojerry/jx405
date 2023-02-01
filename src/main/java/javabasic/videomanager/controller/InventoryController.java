package javabasic.videomanager.controller;

import javabasic.model.BoardDTO;
import javabasic.videomanager.model.FilmDTO;
import javabasic.videomanager.model.InventoryDTO;
import javabasic.videomanager.model.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryController {
    private Connection connection;

    public InventoryController(Connection connection) {
        this.connection = connection;
    }

//    public void insert(BoardDTO boardDTO) {
//        String query = "INSERT INTO board (title,content,writerId,entry_date,modify_date) VALUES(?,?,?,NOW(),NOW())";
//
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            pstmt.setString(1, boardDTO.getTitle());
//            pstmt.setString(2, boardDTO.getContent());
//            pstmt.setInt(3, boardDTO.getWriterId());
//
//            pstmt.executeUpdate();
//
//            pstmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<BoardDTO> selectAll() {
//        ArrayList<BoardDTO> list = new ArrayList<>();
//
//        String query = "SELECT * FROM board ORDER BY board.id DESC";
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(query);
//            ResultSet resultSet = pstmt.executeQuery();
//
//            while (resultSet.next()) {
//                BoardDTO b = new BoardDTO();
//                b.setId(resultSet.getInt("id"));
//                b.setTitle(resultSet.getString("title"));
//                b.setContent(resultSet.getString("content"));
//                b.setWriterId(resultSet.getInt("writerId"));
//                b.setEntryDate(resultSet.getTimestamp("entry_date"));
//                b.setModifyDate(resultSet.getTimestamp("modify_date"));
//
////                Date entry_date = resultSet.getTimestamp("entry_date");
//                list.add(b);
//            }
//            pstmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
public InventoryDTO selectOne(int id) {
    InventoryDTO inventoryDTO = null;
    String query = "SELECT * FROM inventory WHERE inventory_id = ?";
    try {
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);

        ResultSet resultSet = pstmt.executeQuery();

        if (resultSet.next()) {
            inventoryDTO = new InventoryDTO();
            inventoryDTO.setInventory_id(resultSet.getInt("inventory_id"));
            inventoryDTO.setFilm_id(resultSet.getInt("film_id"));

        }
        resultSet.close();
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return inventoryDTO;
}
    public ArrayList<InventoryDTO> selectList(int film_id, StaffDTO staffDTO) {
        ArrayList<InventoryDTO> list = new ArrayList<>();

        String query = "SELECT * FROM inventory WHERE film_id = ? and store_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,film_id);
            pstmt.setInt(2,staffDTO.getStore_id());
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                InventoryDTO i = new InventoryDTO();
                i.setInventory_id(resultSet.getInt("inventory_id"));
                i.setFilm_id(resultSet.getInt("film_id"));

                list.add(i);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
