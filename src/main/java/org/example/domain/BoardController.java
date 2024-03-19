package org.example.domain;

import org.example.base.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardController {
    BoardRepository boardRepository = new BoardRepository();
    Scanner sc = new CommonUtil().getScanner();
    BoardView boardView = new BoardView();
    int WRONG_VALUE = -1; // 값의 의미를 부여

    public void list(){
        boardView.printList(boardRepository.getTxtBoardList());
    }

    public void delete() {
        System.out.print("삭제할 게시물 번호 : ");

        int id = getParamAsInt(sc.nextLine(), WRONG_VALUE);

        if (id == WRONG_VALUE) {
            return;
        }

        Board board = boardRepository.findBoardById(id);
        if (board == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        boardRepository.removeBoardListById(id);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", board.getId());
    }

    public void page() {
        int currentPage = 1;
        int pageNum = 3;
        List<Board> boardList = boardRepository.getTxtBoardList();
        int pageLeng = (int) Math.ceil((double) boardList.size() / pageNum);
        while (true) {
            if (currentPage < 1) {
                currentPage = 1;
                System.out.println("이전 페이지는 없습니다.");
            } else if (currentPage > pageLeng) {
                currentPage = pageLeng;
                System.out.println("다음 페이지는 없습니다.");
            } else {
                for (int i =(currentPage - 1) * 3; i < (Math.min(currentPage * 3, boardList.size())); i++) {
                    boardView.printList(boardList.get(i));
                }
                System.out.println("================");
                for (int i = 1; i <= pageLeng; i++) {
                    if (i == currentPage) {
                        System.out.printf("[%d]", i);
                    } else {
                        System.out.printf(" %d ", i);
                    }
                }
            }
            System.out.print("페이징 명령어를 입력해주세요 (1. 이전, 2. 다음, 3. 선택, 4. 뒤로가기): ");
            int choice = getParamAsInt(sc.nextLine(), WRONG_VALUE);

            if (choice == WRONG_VALUE) {
                return;
            }

            switch (choice) {
                case 1 -> currentPage--;
                case 2 -> currentPage++;
                case 3 -> {
                    while (true) {
                        System.out.print("이동하실 페이지 번호를 입력해주세요 : ");
                        int choicePage = Integer.parseInt(sc.nextLine());
                        if (choicePage < 1 || choicePage > pageLeng) {
                            System.out.println("페이지 번호가 없습니다.");
                        }
                        currentPage = choicePage;
                        break;
                    }
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    public void sort() {
        System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
        int sortTarget = getParamAsInt(sc.nextLine(), WRONG_VALUE);

        if (sortTarget == WRONG_VALUE) {
            return;
        }
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
        int sortOrder = getParamAsInt(sc.nextLine(), WRONG_VALUE);

        if (sortOrder == WRONG_VALUE) {
            return;
        }

        switch(sortTarget){
            case 1, 2 -> {
                boardRepository.sortBoardList(sortTarget, sortOrder);
                boardView.printList(boardRepository.getTxtBoardList());
            }
            default -> System.out.println("올바른 명령어가 아닙니다.");
        }
    }

    public void search() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String searchTitle = sc.nextLine();
        ArrayList<Board> searchedBoard =  boardRepository.findBoardByKeyword(searchTitle);
        if (searchedBoard.isEmpty()) {
            System.out.println("==================");
            System.out.println("검색 결과가 없습니다.");
            System.out.println("==================");
            return;
        }
        System.out.println("==================");
        boardView.printList(searchedBoard);
    }

    public void detail(User loginUser) {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int id = getParamAsInt(sc.nextLine(), WRONG_VALUE);

        if (id == WRONG_VALUE) {
            return;
        }

        Board board = boardRepository.findBoardById(id);

        if (board == null) {
            System.out.println("없는 번호입니다.");
            return;
        }

        boardView.printDetailList(board);

        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int choice = getParamAsInt(sc.nextLine(), WRONG_VALUE);

            if (choice == WRONG_VALUE) {
                return;
            }

            switch(choice){
                case 1 -> {
                    System.out.print("댓글 내용 : ");
                    String commentTitle = sc.nextLine();

                    boardRepository.insertCommentByBoardId(commentTitle, id);

                    boardView.printDetailList(board);
                }
                case 2 -> {
                    if (boardRepository.recommendToggleByBoardId(id, loginUser)) {
                        System.out.println("게시물에 추천을 했습니다.");
                    } else {
                        System.out.println("게시물 추천을 해제하였습니다.");
                    }
                }
                case 3 -> {
                    if (!boardRepository.isMeMadeUserByBoardId(id, loginUser.getId())) {
                        System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
                        continue;
                    }
                    System.out.print("게시물 제목을 입력해주세요 : ");
                    String title = sc.nextLine();
                    System.out.print("게시물 내용을 입력해주세요 : ");
                    String content = sc.nextLine();

                    boardRepository.updateBoardList(id, title, content);

                    System.out.println("수정이 완료되었습니다.");
                }
                case 4 -> {
                    if (!boardRepository.isMeMadeUserByBoardId(id, loginUser.getId())) {
                        System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
                        continue;
                    }
                    boardRepository.removeBoardListById(id);

                    System.out.println("삭제가 완료되었습니다.");
                }
                case 5 -> {
                    System.out.println("상세보기 화면을 빠져나갑니다.");
                    return;
                }

                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    public void update() {
        System.out.print("수정할 게시물 번호 : ");
        int id = getParamAsInt(sc.nextLine(), WRONG_VALUE);

        if (id == WRONG_VALUE) {
            return;
        }

        Board board = boardRepository.findBoardById(id);

        if (board == null) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = sc.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = sc.nextLine();

        boardRepository.updateBoardList(id, title, content);
    }

    public User login() {
        while (true) {
            System.out.print("아이디 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호 : ");
            String password = sc.nextLine();

            User user = boardRepository.findEqualUser(id, password);

            if (user != null) {
                System.out.printf("%s님 환영합니다!\n", user.getNickname());
                return user;
            } else {
                System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
                return null;
            }
        }
    }

    public void signup() {
        System.out.println("==== 회원 가입을 진행합니다 ====");
        System.out.print("아이디를 입력해주세요 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = sc.nextLine();

        boardRepository.insertUserList(id, password, nickname);

        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    public void notLoginCmd() {
        System.out.print("명령어 : ");
    }

    public void loginCmd(User loginUser) {
        System.out.printf("명령어를 입력해주세요[%s(%s)] : ", loginUser.getId(), loginUser.getNickname());
    }

    public void add(User loginUser) {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = sc.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = sc.nextLine();

        boardRepository.insertBoardList(title, content, loginUser.getId());
        System.out.println("게시물이 등록되었습니다.");
    }

    private int getParamAsInt(String param, int defaultValue) {
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return defaultValue;
        }
    }
}

