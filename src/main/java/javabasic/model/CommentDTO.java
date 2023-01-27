package javabasic.model;

public class CommentDTO {
    private int id;
    private String writer;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public boolean equals(Object o) {
        if (o instanceof CommentDTO) {
            CommentDTO b = (CommentDTO) o;
            return id == b.id;
        }
        return false;
    }

    public CommentDTO(CommentDTO origin) {
        id = origin.id;
        writer = origin.writer;
        comment = origin.comment;
    }

    public CommentDTO() {

    }

    public CommentDTO(int id) {
        this.id = id;
    }
}
