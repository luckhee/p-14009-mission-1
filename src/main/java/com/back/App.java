package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    Scanner scanner = new Scanner(System.in);
    infor infor = null;
    int lastId = 0;
    ArrayList<infor> list = new ArrayList<>();

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?id=")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정?id=")) {
                actionModify(cmd);
            }
        }
        scanner.close();
    }

    void actionWrite() {

        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        write(content, author);

        System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    void modifyWrite(infor infor) {

        System.out.println("명언(기존) : " + infor.wiseSaying);
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.println("작가(기존) : " + infor.wiseSayingAuthor);
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        setWrite(infor ,content, author);

        //System.out.println(lastId + "번 명언이 등록되었습니다.");



    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("______________________");

        findForList();


    }

    infor write (String content, String author) {
        infor infor = new infor();

        infor.no = ++lastId;
        infor.wiseSayingAuthor = author;
        infor.wiseSaying = content;
        list.add(infor);

        return infor;
    }

    ArrayList<infor> findForList() {
        ArrayList<infor> forListWiseSayings = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {
            infor a = list.get(i);
            System.out.println("%d /  %s / %s".formatted(
                    a.no,
                    a.wiseSayingAuthor,
                    a.wiseSaying
            ));
        }

        return forListWiseSayings;

    }

    void actionDelete(String cmd) {
        String[] cmdBit = cmd.split("=",2);

        if(cmdBit.length < 2 || cmdBit[1].isEmpty()) {
            System.out.println("id를 입력해주세요");
            return;
        }

        int id = Integer.parseInt(cmdBit[1]);

        int deletedIndex = delete(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }
    }

    int delete (int num) {
        int deletedIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).no == num) {
                list.remove(i);
                System.out.println(num+ "번 명언이 삭제되었습니다.");
                deletedIndex = i;
                break;
            }
        }

        return deletedIndex;
    }

    void actionModify(String cmd) {
        String[] cmdBit = cmd.split("=",2);

        if(cmdBit.length < 2 || cmdBit[1].isEmpty()) {
            System.out.println("id를 입력해주세요");
            return;
        }

        int id = Integer.parseInt(cmdBit[1]);

        int deletedIndex = modify(id);

        if(deletedIndex == -1) {
            System.out.println(id+"번 명언은 존재하지 않습니다.");
        }

    }

    int modify(int id) {
       int modifyIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).no == id) {
                infor infor = list.get(i);
                modifyIndex=i;
                modifyWrite(infor);
            }
        }

        return modifyIndex;
    }

    infor setWrite (infor infor,String content, String author) {


        infor.wiseSayingAuthor = author;
        infor.wiseSaying = content;

        return infor;

    }
}

