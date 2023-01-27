package javabasic.model;


public class BoardDTO2 {
    private int id;
    private String title;
    private int writerId;
    private String writerNickname;
    private String content;

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterNickname() {
        return writerNickname;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (o instanceof BoardDTO2) {
            BoardDTO2 b = (BoardDTO2) o;
            return id == b.id;
        }
        return false;
    }

    public BoardDTO2(BoardDTO2 origin) {
        id = origin.id;
        title = origin.title;
        writerId = origin.writerId;
        writerNickname = origin.writerNickname;
        content = origin.content;
    }

    public BoardDTO2() {

    }

    public BoardDTO2(int id) {
        this.id = id;
    }
}