package org.example.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CommonUtil implements Serializable {
    private Scanner sc = new Scanner(System.in);
    public Scanner getScanner(){
        return sc;
    }

    public String calculateToday() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(today);
    }

}
