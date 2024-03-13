package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Model {
    private ArrayList<Board> boardList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();

    public void getDummy() {
        Date todayTest = new Date();
        SimpleDateFormat dateFormatTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int idNum = 1;
        int userIdNum = 1;

        Board board1 = new Board(idNum++, "안녕하세요 반갑습니다. 자바 공부중이에요.", "자바 너무 재밌어요!!", dateFormatTest.format(todayTest), "test");
        Board board2 = new Board(idNum++, "자바 질문좀 할게요~", "자바 질문좀 할게요~ 내용", dateFormatTest.format(todayTest), "test");
        Board board3 = new Board(idNum++, "정처기 따야되나요?", "정처기 따야되나요? 내용", dateFormatTest.format(todayTest), "test");
        Board board4 = new Board(idNum++, "안녕하세요 반갑습니다. 자바 공부중이에요.", "자바 너무 재밌어요!!", dateFormatTest.format(todayTest), "test");
        Board board5 = new Board(idNum++, "자바 질문좀 할게요~", "자바 질문좀 할게요~ 내용", dateFormatTest.format(todayTest), "test");
        Board board6 = new Board(idNum++, "정처기 따야되나요?", "정처기 따야되나요? 내용", dateFormatTest.format(todayTest), "test");
        Board board7 = new Board(idNum++, "정처기 따야되나요?", "정처기 따야되나요? 내용", dateFormatTest.format(todayTest), "test");

        User testUser = new User(userIdNum++, "test", "test", "테스트계정");

        boardList.add(board1);
        boardList.add(board2);
        boardList.add(board3);
        boardList.add(board4);
        boardList.add(board5);
        boardList.add(board6);
        boardList.add(board7);

        userList.add(testUser);

    }

    Model() {
        getDummy();
        this.idNum = boardList.size();
        this.userIdNum = userList.size();
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    private User loginUser;

    private int idNum = 0;

    private int userIdNum = 0;

    public void addUser(String id, String password, String nickname) {
        User user = new User(++userIdNum, id, password, nickname);
        userList.add(user);
    }

    public User loginUser(String id, String password) {
        for (User user : userList) {
            loginUser = user;
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addBoard(String title, String content) {
        String today = getToday();
        Board b1 = new Board(++idNum, title, content, today, loginUser.getId());
        boardList.add(b1);
    }

    public int findIndexById(int inputId) {
        for (int i = 0; i < boardList.size(); i++) {
            Board board = boardList.get(i);
            if (board.getId() == inputId) {
                return i;
            }
        }
        return -1;
    }

    public void updateBoard(int id, String title, String content) {
        Board findBoard = boardList.get(id);
        findBoard.setTitle(title);
        findBoard.setContent(content);
    }

    public ArrayList<Board> searchBoard(String searchTitle) {
        ArrayList<Board> searchList = new ArrayList<>();
        for (Board board : boardList) {
            if (board.getTitle().contains(searchTitle)) {
                searchList.add(board);
            }
        }
        return searchList;
    }

    public void removeBoard(int id) {
        boardList.remove(id);
    }

    public void addComment(String title, int id) {
        String today = getToday();
        int commentIdx = boardList.get(id).getComment().size();
        commentIdx++;
        Comment comment = new Comment(commentIdx, title, today, id);
        boardList.get(id).getComment().add(comment);
    }

    public boolean recommendToggle(int id) {
        ArrayList<User> currentRecommend = boardList.get(id).getRecommend();
        if (currentRecommend.isEmpty()) {
            currentRecommend.add(loginUser);
            return true;
        }
        if (currentRecommend.contains(loginUser)) {
            currentRecommend.remove(loginUser);
            return false;
        } else {
            currentRecommend.add(loginUser);
            return true;
        }
    }

    public boolean isEqualPostUser(int id) {
        return boardList.get(id).getMadeUser().equals(loginUser.getId());
    }

    public ArrayList<Board> getBoardList() {
        return boardList;
    }

    public String getToday() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(today);
    }

    public void setBoardList(ArrayList<Board> boardList) {
        this.boardList = boardList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void sorting(int standard, int order) {
        if (standard == 1) {
            Collections.sort(boardList, new Comparator<Board>() {
                @Override
                public int compare(Board b1, Board b2) {
                    if (order == 1) {
                        return b1.getId() - b2.getId();
                    } else if (order == 2) {
                        return b2.getId() - b1.getId();
                    }
                    return 0;
                }
            });
        } else if (standard == 2) {
            Collections.sort(boardList, new Comparator<Board>() {
                @Override
                public int compare(Board b1, Board b2) {
                    if (order == 1) {
                        return b1.getCount() - b2.getCount();
                    } else if (order == 2) {
                        return b2.getCount() - b1.getCount();
                    }
                    return 0;
                }
            });
        }
    }

}

