package org.example.base;

import org.example.domain.BoardController;
import org.example.domain.User;

import java.util.*;

public class BoardApp {
    BoardController boardController = new BoardController();
    public static User loginUser = null;
    String cmd;
    Scanner sc = new CommonUtil().getScanner();

    public void run() {
        while (true) {
            if (loginUser != null) {
                boardController.loginCmd(loginUser);
            } else {
                boardController.notLoginCmd();
            }
            cmd = sc.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            if (cmd.equals("signup")) {
                boardController.signup();
                continue;
            } else if (cmd.equals("login")) {
                loginUser = boardController.login();
                continue;
            }

            if (loginUser == null) {
                System.out.println("로그인한 사람만 접근가능합니다.");
            } else {
                switch (cmd) {
                    case "add" -> boardController.add(loginUser);
                    case "list" -> boardController.list();
                    case "update" -> boardController.update();
                    case "delete" -> boardController.delete();
                    case "detail" -> boardController.detail(loginUser);
                    case "search" -> boardController.search();
                    case "sort" -> boardController.sort();
                    case "page" -> boardController.page();
                    default -> System.out.println("올바른 명령어가 아닙니다.");
                }
            }
        }
    }
}