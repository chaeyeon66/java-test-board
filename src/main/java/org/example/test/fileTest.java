package org.example.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileTest {
    public static void main(String[] args) {
        // 저장할 객체 리스트 생성
//        List<Board> objectList = new ArrayList<>();
//        objectList.add(new Board(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "자바 너무 재밌어요!!",  "test"));
//        objectList.add(new Board(2, "자바 질문좀 할게요~", "자바 질문좀 할게요~ 내용",  "test"));
//
//        // 파일 경로 지정
//        String filePath = "./boardTestList.txt";
//
//        int intValue = 42;
//
//        // 객체 리스트와 int 변수를 텍스트 파일에 저장
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
//            // Person 객체를 텍스트로 저장
//            for (Board board : objectList) {
//                writer.println(board.getTitle() + "," + board.getContent());
//            }
//
//            // int 변수를 텍스트로 저장
//            writer.println(intValue);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 파일에서 객체 리스트와 int 변수를 읽어와서 사용
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            List<Board> readPersonList = new ArrayList<>();
//            String line;
//
//            // 파일에서 Person 객체를 읽어와서 리스트에 추가
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(",");
//                String name = parts[0];
//                int age = Integer.parseInt(parts[1]);
////                readPersonList.add(new Person(name, age));
//            }
//
//            // 파일에서 int 변수를 읽어옴
//            int readIntValue = Integer.parseInt(reader.readLine());
//
//            // 읽어온 객체 리스트 출력
//            System.out.println("Object List:");
////            for (Person person : readPersonList) {
////                System.out.println(person);
////            }
//
//            // 읽어온 int 변수 출력
//            System.out.println("Int Value: " + readIntValue);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(42);
        intList.add(100);

        // int ArrayList를 텍스트 파일에 저장
        try (PrintWriter writer = new PrintWriter(new FileWriter("varIntTest.txt"))) {
            for (int i : intList) {
                writer.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 파일에서 int ArrayList를 읽어와서 사용
        ArrayList<Integer> readIntList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("varIntTest.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line);
                readIntList.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 읽어온 int ArrayList 출력
        System.out.println("Read int ArrayList:");
        for (int value : readIntList) {
            System.out.println(value);
        }


    }
}