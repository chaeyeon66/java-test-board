package org.example;

import java.util.*;

public class BoardApp {
    Model model = new Model();
    Scanner sc = new Scanner(System.in);
    String cmd;

    public void run() {
//        System.out.print("명령어 : ");
//        String cmd = sc.nextLine();

        // 반복 횟수를 정할 수 없다. => 무한 반복 구조
        while (true) { // 무한 반복
            if (model.getLoginUser() != null) {
                loginCmd();
            } else {
                notLoginCmd();
            }

            if (cmd.equals("exit")) { // 숫자가 아닌 다른 타입의 같다라는 표현을 할 때 == 가 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (cmd.equals("signup")) {
                signup();
            } else if (cmd.equals("login")) {
                login();
            }

            if (model.getLoginUser() == null) {
                System.out.println("로그인한 사람만 접근가능합니다.");
            } else {
                if (cmd.equals("add")) {
                    add();
                } else if (cmd.equals("list")) {
                    printList(model.getBoardList());
                } else if (cmd.equals("update")) {
                    update();
                } else if (cmd.equals("delete")) {
                    System.out.print("삭제할 게시물 번호 : ");
                    int id = Integer.parseInt(sc.nextLine());

                    int index = model.findIndexById(id);
                    if (index == -1) {
                        System.out.println("없는 게시물 번호입니다.");
                        continue;
                    }

                    model.removeBoard(index);

                    System.out.printf("%d번 게시물이 삭제되었습니다.\n", index);

                } else if (cmd.equals("detail")) {
                    detail();
                } else if (cmd.equals("search")) {
                    search();
                } else if (cmd.equals("sort")) {
                    sort();
                } else if (cmd.equals("page")) {
                    page();
                }

            }
        }

        // 1. 반복문 제어하는 방법 : 반복 횟수 세서 특정 횟수 지나면 탈출
        // 2. break문을 사용하여 강제 탈출 가능

    }

    private void page() {
        int currentPage = 1;
        int pageNum = 3;
        ArrayList<Board> boardList = model.getBoardList();
        int pageLeng = (int) Math.ceil((double) boardList.size() / pageNum);
        while (true) {
            if (currentPage < 1) {
                currentPage = 1;
                System.out.println("이전 페이지는 없습니다.");
            } else if (currentPage > pageLeng) {
                currentPage = pageLeng;
                System.out.println("다음 페이지는 없습니다.");
            } else {
                for (int i = (currentPage - 1) * 3; i < (Math.min(currentPage * 3, boardList.size())); i++) {
                    printList(boardList.get(i));
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
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                currentPage--;
            } else if (choice == 2) {
                currentPage++;
            } else if (choice == 3) {
                while (true) {
                    System.out.print("이동하실 페이지 번호를 입력해주세요 : ");
                    int choicePage = Integer.parseInt(sc.nextLine());
                    if (choicePage < 1 || choicePage > pageLeng) {
                        System.out.println("페이지 번호가 없습니다.");
                    }
                    currentPage = choicePage;
                    break;
                }
            } else if (choice == 4) {
                break;
            }
        }
    }

    private void sort() {
        System.out.print("정렬 대상을 선택해주세요. (1. 번호,  2. 조회수) : ");
        int sortTarget = Integer.parseInt(sc.nextLine());
        System.out.print("정렬 방법을 선택해주세요. (1. 오름차순,  2. 내림차순) : ");
        int sortOrder = Integer.parseInt(sc.nextLine());
        if (sortTarget == 1) {
            model.sorting(sortTarget, sortOrder);
            printList(model.getBoardList());
        } else if (sortTarget == 2) {
            model.sorting(sortTarget, sortOrder);
            printList(model.getBoardList());
        }
    }

    private void search() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String searchTitle = sc.nextLine();
        ArrayList<Board> searchedBoard = model.searchBoard(searchTitle);
        if (searchedBoard.isEmpty()) {
            System.out.println("==================");
            System.out.println("검색 결과가 없습니다.");
            System.out.println("==================");
            return;
        }
        System.out.println("==================");
        printList(searchedBoard);
    }

    private void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int id = Integer.parseInt(sc.nextLine());

        int findIdx = model.findIndexById(id);

        if (findIdx == -1) {
            System.out.println("없는 번호입니다.");
            return;
        }

        Board board = model.getBoardList().get(findIdx);

        printDetailList(board);

        while (true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                System.out.print("댓글 내용 : ");
                String commentTitle = sc.nextLine();

                model.addComment(commentTitle, findIdx);

                printDetailList(board);
            } else if (choice == 2) {
                if (model.recommendToggle(findIdx)) {
                    System.out.println("게시물에 추천을 했습니다.");
                } else {
                    System.out.println("게시물 추천을 해제하였습니다.");
                }
            } else if (choice == 3) {
                if (!model.isEqualPostUser(findIdx)) {
                    System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
                    continue;
                }
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = sc.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String content = sc.nextLine();

                model.updateBoard(findIdx, title, content);

                System.out.println("수정이 완료되었습니다.");

            } else if (choice == 4) {
                if (!model.isEqualPostUser(findIdx)) {
                    System.out.println("자신의 게시물만 수정/삭제 할 수 있습니다.");
                    continue;
                }
                model.removeBoard(findIdx);

                System.out.println("삭제가 완료되었습니다.");
            } else if (choice == 5) {
                System.out.println("상세보기 화면을 빠져나갑니다.");
                break;
            }
        }
    }

    private void update() {
        System.out.print("수정할 게시물 번호 : ");
        int id = Integer.parseInt(sc.nextLine());

        int index = model.findIndexById(id);

        if (index == -1) {
            System.out.println("없는 게시물 번호입니다.");
            return;
        }

        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = sc.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = sc.nextLine();

        model.updateBoard(index, title, content);
    }

    private void login() {
        while (true) {
            System.out.print("아이디 : ");
            String id = sc.nextLine();
            System.out.print("비밀번호 : ");
            String password = sc.nextLine();

            User user = model.loginUser(id, password);

            if (user != null) {
                System.out.printf("%s님 환영합니다!\n", user.getNickname());
                break;
            } else {
                System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
            }
        }
    }

    private void signup() {
        System.out.println("==== 회원 가입을 진행합니다 ====");
        System.out.print("아이디를 입력해주세요 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = sc.nextLine();

        model.addUser(id, password, nickname);

        System.out.println("==== 회원가입이 완료되었습니다. ====");
    }

    private void notLoginCmd() {
        System.out.print("명령어 : ");
        cmd = sc.nextLine();
    }

    private void loginCmd() {
        User loginedUser = model.getLoginUser();
        System.out.printf("명령어를 입력해주세요[%s(%s)] : ", loginedUser.getId(), loginedUser.getNickname());
        cmd = sc.nextLine();
    }

    public void printList(ArrayList<Board> targetBoard) {
        for (Board board : targetBoard) {
            System.out.println("==================");
            System.out.printf("번호 : %s\n", board.getId());
            System.out.printf("제목 : %s\n", board.getTitle());
            System.out.printf("조회수 : %d\n", board.getCount());
        }
        System.out.println("================");
    }

    public void printList(Board targetBoard) {
        System.out.println("==================");
        System.out.printf("번호 : %s\n", targetBoard.getId());
        System.out.printf("제목 : %s\n", targetBoard.getTitle());
        System.out.printf("조회수 : %d\n", targetBoard.getCount());
    }

    public void printDetailList(Board board) {
        System.out.println("==================");
        System.out.printf("번호 : %s\n", board.getId());
        System.out.printf("제목 : %s\n", board.getTitle());
        System.out.printf("내용 : %s\n", board.getContent());
        System.out.printf("등록날짜 : %s\n", board.getToday());
        board.increaseCount();
        System.out.printf("조회수 : %d\n", board.getCount());
        System.out.printf("좋아요수 : %d\n", board.getRecommend().size());
        System.out.println("==================");
        if (!(board.getComment().isEmpty())) {
            System.out.println("======= 댓글 =======");
            for (Comment showedCommt : board.getComment()) {
                System.out.printf("댓글 내용  : %s\n", showedCommt.getContent());
                System.out.printf("댓글 작성일 : %s\n", showedCommt.getToday());
                System.out.println("==================");
            }
        }
    }

    public void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = sc.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = sc.nextLine();

        model.addBoard(title, content);
        System.out.println("게시물이 등록되었습니다.");
    }
}