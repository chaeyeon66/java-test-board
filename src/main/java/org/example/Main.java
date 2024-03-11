package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.print("명령어 : ");
//        String cmd = sc.nextLine();
        ArrayList<Board> boardList = new ArrayList<>();
        int idNum = 0;

        // 반복 횟수를 정할 수 없다. => 무한 반복 구조
        while (true) { // 무한 반복
            System.out.print("명령어 : ");
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) { // 숫자가 아닌 다른 타입의 같다라는 표현을 할 때 == 가 아닌 .equals()를 사용해야 한다.
                System.out.println("프로그램을 종료합니다.");
                break;
            }else if (cmd.equals("add")) {
                Board b1 = new Board();
                idNum++;
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = sc.nextLine();
                b1.title = title;
                System.out.print("게시물 내용을 입력해주세요 : ");
                String content = sc.nextLine();
                b1.content = content;
                b1.id = idNum;
                boardList.add(b1);
                System.out.println("게시물이 등록되었습니다.");
            }else if(cmd.equals("list")){
                System.out.println("==================");
                for (Board board : boardList) {
                    System.out.printf("번호 : %s\n", board.id);
                    System.out.printf("제목 : %s\n", board.title);
                    System.out.println("==================");
                }
            }else if(cmd.equals("modify")){
                System.out.print("수정할 게시물 번호 : ");
                int id = Integer.parseInt(sc.nextLine());
                boolean flag = false;
                for(Board board : boardList) {
                    if (board.id == id) {
                        System.out.print("게시물 제목을 입력해주세요 : ");
                        String title = sc.nextLine();
                        System.out.print("게시물 내용을 입력해주세요 : ");
                        String content = sc.nextLine();
                        board.title = title;
                        board.content = content;
                        System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    System.out.println("없는 게시물 번호입니다.");
                }
            }else if(cmd.equals("delete")){
                System.out.print("삭제할 게시물 번호 : ");
                int id = Integer.parseInt(sc.nextLine());
                boolean flag = false;
                for(Board board : boardList) {
                    if (board.id == id) {
                        boardList.remove(board);
                        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    System.out.println("없는 게시물 번호입니다.");
                }
            }else if(cmd.equals("detail")){
                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(sc.nextLine());
                boolean flag = false;
                for(Board board : boardList) {
                    if (board.id == id) {
                        System.out.println("==================");
                        System.out.printf("번호 : %s\n", board.id);
                        System.out.printf("제목 : %s\n", board.title);
                        System.out.printf("내용 : %s\n", board.content);
                        System.out.println("==================");
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    System.out.println("없는 게시물 번호입니다.");
                }
            }

        }

        // 1. 반복문 제어하는 방법 : 반복 횟수 세서 특정 횟수 지나면 탈출
        // 2. break문을 사용하여 강제 탈출 가능

    }
}

class Board {
    int id;
    String title;
    String content;
}