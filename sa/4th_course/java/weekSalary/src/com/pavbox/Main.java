package com.pavbox;


/**
 * Написать метод, высчитывающий зарплату работника по отработанным часам и ставке.
 * Если значение hours более 60, то выводим сообщение о невозможности перевести деньги за такое количество часов.
 */
public class Main {

    final static int MAX_HOURS_FOR_WEEK = 60;

    public static void main(String[] args) {

        Main.showSalaryBy(60, 100);
        Main.showSalaryBy(45, 100);
        Main.showSalaryBy(61, 100);

    }

    private static void showSalaryBy(float hours, float rate) {

        if (hours <= MAX_HOURS_FOR_WEEK) {
            float amount = hours * rate;
            System.out.println(amount);
        } else {
            System.out.printf("[ # ] Can't transfer salary for %.2f hours per week.\n[ ! ] Please, enter another hours value (less than %d hours).", hours, MAX_HOURS_FOR_WEEK);
        }

    }

}
