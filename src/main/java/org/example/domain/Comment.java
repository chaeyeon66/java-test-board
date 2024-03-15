package org.example.domain;

import org.example.base.CommonUtil;

import java.io.Serializable;

public class Comment implements Serializable {
    Comment(int id, String content, int boardId){

        CommonUtil commonUtil = new CommonUtil();

        this.id = id;
        this.content = content;
        this.today = commonUtil.calculateToday();
        this.boardId = boardId;
    }
    private int id;
    private String content;
    private String today;
    private int boardId;

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
}
