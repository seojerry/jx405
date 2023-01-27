package javabasic.controller;

import javabasic.model.BoardDTO;
import javabasic.model.CommentDTO;

import java.util.ArrayList;

public class CommentController {
    private ArrayList<CommentDTO> list;
    private int nextId;

    public CommentController() {
        list = new ArrayList<>();
        nextId = 1;
    }
    public void add(CommentDTO commentDTO) {
        commentDTO.setId(nextId++);
        list.add(commentDTO);
    }

    public CommentDTO selectOne(int id) {
        CommentDTO temp = new CommentDTO(id);
        if (list.contains(temp)) {
            return new CommentDTO(list.get(list.indexOf(temp)));
        }

        return null;
    }

    public ArrayList<CommentDTO> selectAll() {
        ArrayList<CommentDTO> temp = new ArrayList<>();
        for (CommentDTO b : list) {
            temp.add(new CommentDTO(b));
        }

        return temp;
    }

    public void update(CommentDTO commentDTO) {
        list.set(list.indexOf(commentDTO), commentDTO);
    }

    public void delete(int id) {
        list.remove(new CommentDTO(id));
    }
}
