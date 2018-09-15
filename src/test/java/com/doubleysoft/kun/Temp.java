package com.doubleysoft.kun;

import java.util.Date;

/**
 * Created by anguslean
 * 18-9-15 下午4:43
 */
public class Temp {
    public static void main(String[] args) {
        Date d1 = new Date("1 Apr 98");
        nextDateUpdate(d1);
        System.out.println("d1 after nextDay: " + d1);

        Date d2 = new Date("1 Apr 98");
        nextDateReplace(d2);
        System.out.println("d1 after next day :" + d2);

    }

    private static void nextDateUpdate(Date date) {
        date.setDate(date.getDate() + 1);
        System.out.println("arg in next Day: " + date);
    }

    private static void nextDateReplace(Date date) {
        date = new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
        System.out.println("arg in nextDay: " + date);
    }
}
