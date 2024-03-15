package org.example.test;

import java.util.Scanner;

public class ExceptionTest {
    public static void main(String[] args) {
//        int[] numbers = {1,2,3};
//        Car car = null;
        Scanner sc = new Scanner(System.in);

//       System.out.println(numbers[3]);

//        car.drive();

//        Integer.parseInt(sc.nextLine());

        System.out.println("hihi");
        // 익셉션 -> 발생 -> 프로그램이 종료

        // 익셉션 핸들링 -> 예외처리
        // try catch
        try{
            int[] numbers = {1,2,3};
            System.out.println(numbers[2]);
            Car car = new Car();
            car.drive();
            Integer.parseInt(sc.nextLine());
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("배열의 인덱스를 다시 확인해주세요");
        } catch(NullPointerException e){
            System.out.println("객체를 다시 확인해주세요");
        }catch (NumberFormatException e){
            System.out.println("숫자를 입력해주세요.");
        }
        System.out.println("byebye");

    }
}

class Car{
    public void drive(){
        System.out.println("drive");
    }
}


