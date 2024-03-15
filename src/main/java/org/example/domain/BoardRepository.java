package org.example.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoardRepository {
    private List<Board> boardList = getTxtBoardList();
    private List<User> userList = getTxtUserList();
    private ArrayList<Integer> idNumsList = getTxtIdNumsList();
    private int idNum = idNumsList.get(1);
    private int userIdNum = idNumsList.get(0);

//    public BoardRepository(){
//        resetBoardList();
//        resetUserList();
//    }

    public void resetBoardList(){
        List<Board> board = new ArrayList<>();

        Board b1 = new Board(1,"t1","d1","test");
        Board b2 = new Board(2,"t2","d2","test");
        Board b3 = new Board(3,"t3","d3","test");
        Board b4 = new Board(4,"t4","d4","test");
        Board b5 = new Board(5,"t5","d5","test");
        Board b6 = new Board(6,"t6","d6","test");
        Board b7 = new Board(7,"t7","d7","test");

        board.add(b1);
        board.add(b2);
        board.add(b3);
        board.add(b4);
        board.add(b5);
        board.add(b6);
        board.add(b7);

        this.setTxtBoardList(board);
    }

    public void resetUserList(){
        List<User> user = new ArrayList<>();

        User testUser = new User(1, "test", "test", "테스트계정");

        user.add(testUser);

        this.setTxtUserList(user);
    }

    public void insertUserList(String id, String password, String nickname){
        User user = new User(userIdNum++, id, password, nickname);
        idNumsList.set(0, userIdNum);
        setTxtIdNumsList(idNumsList);
        userList.add(user);
        setTxtUserList(userList);
    }

    public User findEqualUser(String id, String password){
        for (User user : userList) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void insertBoardList(String title, String content, String id){
        Board b1 = new Board(idNum++, title, content, id);
        idNumsList.set(1,idNum);
        setTxtIdNumsList(idNumsList);
        boardList.add(b1);
        setTxtBoardList(boardList);
    }

    public Board findBoardById(int inputId) {
        for (Board board : boardList) {
            if (board.getId() == inputId) {
                return board;
            }
        }
        return null;
    }

    public void sortBoardList(int standard, int order){
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
            setTxtBoardList(boardList);
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
            setTxtBoardList(boardList);
        }
    }

    public void updateBoardList(int id, String title, String content){
        Board board = this.findBoardById(id);
        board.setTitle(title);
        board.setContent(content);
        setTxtBoardList(boardList);
    }

    public void removeBoardListById(int id){
        Board board = this.findBoardById(id);
        boardList.remove(board);
        setTxtBoardList(boardList);
    }

    public ArrayList<Board> findBoardByKeyword(String searchTitle){
        ArrayList<Board> searchList = new ArrayList<>();
        for (Board board : boardList) {
            if (board.getTitle().contains(searchTitle)) {
                searchList.add(board);
            }
        }
        return searchList;
    }

    public void insertCommentByBoardId(String title, int id){
        Board board = this.findBoardById(id);
        int commentIdx = board.getComment().size();
        commentIdx++;
        Comment comment = new Comment(commentIdx, title, id);
        board.getComment().add(comment);
        setTxtBoardList(boardList);
    }

    public boolean recommendToggleByBoardId(int id, User loginUser){
        Board board = this.findBoardById(id);
        ArrayList<User> currentRecommend = board.getRecommend();
        if (currentRecommend.isEmpty()) {
            currentRecommend.add(loginUser);
            setTxtBoardList(boardList);
            return true;
        }
        if (currentRecommend.contains(loginUser)) {
            currentRecommend.remove(loginUser);
            setTxtBoardList(boardList);
            return false;
        } else {
            currentRecommend.add(loginUser);
            setTxtBoardList(boardList);
            return true;
        }
    }

    public boolean isMeMadeUserByBoardId(int id, String loginId){
        Board board = findBoardById(id);
        return board.getMadeUser().equals(loginId);
    }

    public void setTxtBoardList(List<Board> boardList){
        // 파일 경로 지정
        String filePath = "./boardList.txt";
        try {
            // ObjectOutputStream을 사용하여 객체 리스트를 파일에 저장한다.
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(boardList);
            objectOutputStream.close();
            System.out.println("객체 리스트가 파일에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    public List<Board> getTxtBoardList(){
        String filePath = "./boardList.txt";
        try {
            // ObjectInputStream을 사용하여 파일에서 객체 리스트를 읽어온다.
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Board> objectList = (List<Board>) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("파일에서 객체 리스트를 성공적으로 읽어왔습니다.");
            // 읽어온 객체 리스트를 사용하는 코드 추가
            return objectList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("파일 읽기 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
        return null;
    }

    public void setTxtUserList(List<User> userList){
        // 파일 경로 지정
        String filePath = "./userList.txt";
        try {
            // ObjectOutputStream을 사용하여 객체 리스트를 파일에 저장한다.
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);
            objectOutputStream.close();
            System.out.println("객체 리스트가 파일에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    public List<User> getTxtUserList(){
        String filePath = "./userList.txt";
        try {
            // ObjectInputStream을 사용하여 파일에서 객체 리스트를 읽어온다.
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<User> objectList = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("파일에서 객체 리스트를 성공적으로 읽어왔습니다.");
            // 읽어온 객체 리스트를 사용하는 코드 추가
            return objectList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("파일 읽기 중 오류가 발생하였습니다.");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getTxtIdNumsList(){
        ArrayList<Integer> readIntList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("varIdNums.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line);
                readIntList.add(value);
            }
            return readIntList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTxtIdNumsList(ArrayList<Integer>intList){
        try (PrintWriter writer = new PrintWriter(new FileWriter("varIdNums.txt"))) {
            for (int i : intList) {
                writer.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setBoardList(ArrayList<Board> boardList) {
        this.boardList = boardList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

}
