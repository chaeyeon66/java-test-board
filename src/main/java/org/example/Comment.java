package org.example;

public class Comment {
    Comment(int id, String content, String today, int boardId){
        this.id = id;
        this.content = content;
        this.today = today;
        this.boardId = boardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    private int id;
    private String content;
    private String today;
    private int boardId;
}
