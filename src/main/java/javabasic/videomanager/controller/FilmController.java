package javabasic.videomanager.controller;
import javabasic.videomanager.model.FilmDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmController {
    private Connection connection;

    public FilmController(Connection connection) {
        this.connection = connection;
    }

    public FilmDTO selectOne(int id) {
        FilmDTO filmDTO = null;
        String query = "SELECT * FROM film WHERE film_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                filmDTO = new FilmDTO();
                filmDTO.setFilm_id(resultSet.getInt("film_id"));
                filmDTO.setTitle(resultSet.getString("title"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmDTO;
    }

    public FilmDTO selectOneByTitle(String title) {
        FilmDTO filmDTO = null;
        String query = "SELECT * FROM film WHERE title = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, title);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                filmDTO = new FilmDTO();
                filmDTO.setFilm_id(resultSet.getInt("film_id"));
                filmDTO.setTitle(resultSet.getString("title"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmDTO;
    }


}
