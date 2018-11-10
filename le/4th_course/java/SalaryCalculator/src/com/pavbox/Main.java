package com.pavbox;


/**
 * Написать метод, высчитывающий зарплату работника по отработанным часам и ставке.
 * Если у работника есть переработки – они оплачиваются на тарифу x1.5.
 */
public class Main {

    final static int MAX_HOURS = 40;

    public static void main(String[] args) {

        float normalSalary = Main.calculateSalary(39, 100);
        float overtimeSalary = Main.calculateSalary(45, 100);

        System.out.println(normalSalary);
        System.out.println(overtimeSalary);

    }

    private static float calculateSalary(float hours, float rate) {

        float amount = 0.f;

        if (hours > MAX_HOURS) {
            amount = MAX_HOURS * rate;
            amount += (hours - MAX_HOURS) * (rate * 1.5);
        } else {
            amount = hours * rate;
        }

        return amount;
    }
}
