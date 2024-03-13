package org.example;

import java.util.ArrayList;

public class Board implements Comparable<Board>{
    Board(int id, String title, String content, String today, String madeUser){
        this.id = id;
        this.title = title;
        this.content = content;
        this.today = today;
        this.count = 0;
        this.madeUser = madeUser;
        this.comment = new ArrayList<>();
        this.recommend = new ArrayList<>();
    }
    // getter, setter 사용하기
    // private 접근제한자
    // alt + insert

    private int id;
    private int count;
    private String title;
    private String content;
    private String today;
    private String madeUser;

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
    private ArrayList<Comment> comment;

    private ArrayList<User> recommend;

    public String getMadeUser() {
        return madeUser;
    }

    public void setMadeUser(String madeUser) {
        this.madeUser = madeUser;
    }

    public ArrayList<User> getRecommend() {
        return recommend;
    }

    public void setRecommend(ArrayList<User> recommend) {
        this.recommend = recommend;
    }

    @Override
    public int compareTo(Board b){
        return this.getId() - b.getId();
    }
}
