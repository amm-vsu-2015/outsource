package com.pavbox;

/**
 * Написать метод, высчитывающий зарплату работника по отработанным часам и ставке.
 * Если значение hours более 60, то выводим сообщение о невозможности перевести деньги за такое количество часов.
 */
public class Salary {
    final static int max_hours= 60;

    public static void main(String[] args) {


        Salary.Calculate(500, 30);
        Salary.Calculate(500, 67);

    }

    static void Calculate(int st, int hour){

        if (hour>max_hours) {

            System.out.println("ERROR");
        }
        else {

            float zp = hour * st;
            System.out.println(zp);
        }


    }
}
