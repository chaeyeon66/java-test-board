package org.example;

import java.util.ArrayList;

public class Board {
    Board(int id, String title, String content, String today){
        this.id = id;
        this.title = title;
        this.content = content;
        this.today = today;
        this.count = 1;
    }
    // getter, setter 사용하기
    // private 접근제한자
    // alt + insert

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public ArrayList<Comment> getComment() {
        return comment;
    }

    public void setComment(ArrayList<Comment> comment) {
        this.comment = comment;
    }

    private int id;
    private int count;
    private String title;
    private String content;
    private String today;
    private ArrayList<Comment> comment;
}
