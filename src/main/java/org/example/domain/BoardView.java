package org.example.domain;

import java.util.List;

public class BoardView {
    public void printList(List<Board> targetBoard) {
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
}
