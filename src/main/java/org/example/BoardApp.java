package org.example;

import java.util.*;
import java.text.SimpleDateFormat;

public class BoardApp {
    ArrayList<Board> boardList = new ArrayList<>();
    ArrayList<User> userList = new ArrayList<>();

    public void run() {
        Scanner sc = new Scanner(System.in);
//        System.out.print("명령어 : ");
//        String cmd = sc.nextLine();
        int idNum = 1;
        int userIdNum = 1;
        int countNum = 1;
        Date todayTest = new Date();
        SimpleDateFormat dateFormatTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

        boolean isLogined = false;
        User loginUser = new User();


        // 반복 횟수를 정할 수 없다. => 무한 반복 구조
        while (true) { // 무한 반복
            String cmd;
            if (isLogined) {
                System.out.printf("명령어를 입력해주세요[%s(%s)] : ", loginUser.getId(), loginUser.getNickname());
                cmd = sc.nextLine();
            } else {
                System.out.print("명령어 : ");
                cmd = sc.nextLine();
            }

            if (cmd.equals("exit")) { // 숫자가 아닌 다른 타입의 같다라는 표현을 할 때 == 가 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (cmd.equals("signup")) {
                System.out.println("==== 회원 가입을 진행합니다 ====");
                System.out.print("아이디를 입력해주세요 : ");
                String id = sc.nextLine();
                System.out.print("비밀번호를 입력해주세요 : ");
                String password = sc.nextLine();
                System.out.print("닉네임을 입력해주세요 : ");
                String nickname = sc.nextLine();
                User user = new User(userIdNum++, id, password, nickname);
                userList.add(user);
                System.out.println("==== 회원가입이 완료되었습니다. ====");
                continue;

            } else if (cmd.equals("login")) {
                boolean flag = false;
                while (true) {
                    for (User user : userList) {
                        System.out.print("아이디 : ");
                        String id = sc.nextLine();
                        System.out.print("비밀번호 : ");
                        String password = sc.nextLine();
                        loginUser = new User(user.getUserId(), user.getId(), user.getPassword(), user.getNickname());
                        if (user.getId().equals(id) && user.getPassword().equals(password)) {
                            flag = true;
                            System.out.printf("%s님 환영합니다!\n", user.getNickname());
                            break;
                        }
                        if (!flag) {
                            System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
                        }
                    }
                    if (flag) {
                        isLogined = true;
                        break;
                    }
                }
                continue;
            }

            if (!isLogined) {
                System.out.println("로그인한 사람만 접근가능합니다.");
            } else {
                if (cmd.equals("add")) {
                    System.out.print("게시물 제목을 입력해주세요 : ");
                    String title = sc.nextLine();
                    System.out.print("게시물 내용을 입력해주세요 : ");
                    String content = sc.nextLine();
                    Date today = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Board b1 = new Board(idNum++, title, content, dateFormat.format(today), loginUser.getId());
                    boardList.add(b1);
                    System.out.println("게시물이 등록되었습니다.");
                } else if (cmd.equals("list")) {
                    System.out.println("==================");
                    int n = 0;
                    int pageNum = 3;
                    int dan = 3;
                    int leng = boardList.size();

                    while (true) {
                        for (int j = n; j < (Math.min(dan, leng)); j++) {
                            Board board = boardList.get(j);
                            System.out.printf("번호 : %s\n", board.getId());
                            System.out.printf("제목 : %s\n", board.getTitle());
                            System.out.println("==================");
                        }
                        System.out.print("페이지 이동 번호를 눌러주세요(1. 다음 페이지, 2. 이전페이지  3. 돌아가기) : ");
                        int choice = Integer.parseInt(sc.nextLine());
                        if (choice == 1) {
                            n += pageNum;
                            dan += pageNum;
                            if (dan + pageNum >= leng) {
                                System.out.println("다음페이지가 존재하지 않습니다.");
                                dan = leng - pageNum;
                                n = leng;
                            }
                        } else if (choice == 2) {
                            n -= 3;
                            dan -= 3;
                            if (n < 0) {
                                System.out.println("이전페이지가 존재하지 않습니다.");
                                n = 0;
                                dan = 3;
                            }
                        } else if (choice == 3) {
                            System.out.println("되돌아갑니다.");
                            break;
                        }
                    }

                } else if (cmd.equals("update")) {
                    System.out.print("수정할 게시물 번호 : ");
                    int id = Integer.parseInt(sc.nextLine());
                    int index = findIndexById(id);
                    if (index == -1) {
                        System.out.println("없는 게시물 번호입니다.");
                        continue;
                    }

                    Board board = boardList.get(index);
                    System.out.print("게시물 제목을 입력해주세요 : ");
                    String title = sc.nextLine();
                    System.out.print("게시물 내용을 입력해주세요 : ");
                    String content = sc.nextLine();
                    board.setTitle(title);
                    board.setContent(content);

                } else if (cmd.equals("delete")) {
                    System.out.print("삭제할 게시물 번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    int index = findIndexById(id);
                    if (index == -1) {
                        System.out.println("없는 게시물 번호입니다.");
                        continue;
                    }

                    boardList.remove(index);
                    System.out.printf("%d번 게시물이 삭제되었습니다.\n", index);

                } else if (cmd.equals("detail")) {
                    System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean flag = false;
                    for (Board board : boardList) {
                        if (board.getId() == id) {
                            System.out.println("==================");
                            System.out.printf("번호 : %s\n", board.getId());
                            System.out.printf("제목 : %s\n", board.getTitle());
                            System.out.printf("내용 : %s\n", board.getContent());
                            System.out.printf("등록날짜 : %s\n", board.getToday());
                            board.setCount(countNum++);
                            System.out.printf("조회수 : %d\n", board.getCount());
                            System.out.printf("추천수 : %d\n", board.getRecommend().size());
                            System.out.println("==================");
                            if (!(board.getComment().isEmpty())) {
                                System.out.println("======= 댓글 =======");
                                for (Comment showedCommt : board.getComment()) {
                                    System.out.printf("댓글 내용  : %s\n", showedCommt.getContent());
                                    System.out.printf("댓글 작성일 : %s\n", showedCommt.getToday());
                                    System.out.println("==================");
                                }
                            }
                            flag = true;
                            while (true) {
                                System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
                                int choice = Integer.parseInt(sc.nextLine());
                                if (choice == 1) {
                                    System.out.print("댓글 내용 : ");
                                    String commentTitle = sc.nextLine();
                                    Date today = new Date();
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    if (board.getComment() == null) {
                                        board.setComment(new ArrayList<>());
                                    }
                                    int commentIdx = board.getComment().size();
                                    commentIdx++;
                                    Comment comment = new Comment(commentIdx, commentTitle, dateFormat.format(today), board.getId());
                                    board.getComment().add(comment);

                                    System.out.println("==================");
                                    System.out.printf("번호 : %s\n", board.getId());
                                    System.out.printf("제목 : %s\n", board.getTitle());
                                    System.out.printf("내용 : %s\n", board.getContent());
                                    System.out.printf("등록날짜 : %s\n", board.getToday());
                                    board.setCount(countNum++);
                                    System.out.printf("조회수 : %s\n", board.getCount());
                                    System.out.println("==================");
                                    System.out.println("======= 댓글 =======");
                                    for (Comment showedCommt : board.getComment()) {
                                        System.out.printf("댓글 내용  : %s\n", showedCommt.getContent());
                                        System.out.printf("댓글 작성일 : %s\n", showedCommt.getToday());
                                        System.out.println("==================");
                                    }
                                } else if (choice == 2) {
                                    if (board.getRecommend().isEmpty()) {
                                        board.getRecommend().add(loginUser);
                                        System.out.println("게시물에 추천을 했습니다.");
                                        continue;
                                    }
                                    if (board.getRecommend().contains(loginUser)) {
                                        board.getRecommend().remove(loginUser);
                                        System.out.println("게시물 추천을 해제하였습니다.");
                                    } else {
                                        board.getRecommend().add(loginUser);
                                        System.out.println("게시물에 추천을 했습니다.");
                                    }
                                } else if (choice == 3) {
                                    if (!board.getMadeUser().equals(loginUser.getId())) {
                                        System.out.println("작성자만 수정할 수 있습니다.");
                                        continue;
                                    }
                                    System.out.print("게시물 제목을 입력해주세요 : ");
                                    String title = sc.nextLine();
                                    System.out.print("게시물 내용을 입력해주세요 : ");
                                    String content = sc.nextLine();
                                    board.setTitle(title);
                                    board.setContent(content);
                                    System.out.println("수정이 완료되었습니다.");
                                    continue;
                                } else if (choice == 4) {
                                    if (!board.getMadeUser().equals(loginUser.getId())) {
                                        System.out.println("작성자만 삭제할 수 있습니다.");
                                        continue;
                                    }
                                    boardList.remove(board);
                                    System.out.println("삭제가 완료되었습니다.");
                                    continue;
                                } else if (choice == 5) {
                                    System.out.println("상세보기 화면을 빠져나갑니다.");
                                    break;
                                }
                            }
                        }
                    }
                    if (!flag) {
                        System.out.println("없는 게시물 번호입니다.");
                    }
                } else if (cmd.equals("search")) {
                    System.out.print("게시물 제목을 입력해주세요 : ");
                    String searchTitle = sc.nextLine();
                    ArrayList<Board> searchList = new ArrayList<>();
                    boolean flag = false;
                    for (Board board : boardList) {
                        if (board.getTitle().contains(searchTitle)) {
                            searchList.add(board);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println("==================");
                        System.out.println("검색 결과가 없습니다.");
                        System.out.println("==================");
                        continue;
                    }
                    System.out.println("==================");
                    for (Board board : searchList) {
                        System.out.printf("번호 : %s\n", board.getId());
                        System.out.printf("제목 : %s\n", board.getTitle());
                        System.out.println("==================");
                    }

                }
            }
        }

        // 1. 반복문 제어하는 방법 : 반복 횟수 세서 특정 횟수 지나면 탈출
        // 2. break문을 사용하여 강제 탈출 가능

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
}