package com.pavbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Проверка числа на палиндром.
 */

public class Main {

    public static void main(String[] args) {

        try {

            String answer = askUser();

            // check is this number or string. throws if it is not a number.
            Integer.parseInt(answer);

            if (answer.length() < 2) {
                System.out.println("You wrote short value. Please, restart the program with correct value.");
                return;
            }

            if (isPalindrome(answer)) {
                System.out.printf("Number %s is Palindrome.", answer);
            } else {
                System.out.printf("Number %s is NOT Palindrome.", answer);
            }

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: (it is not a number, please restart program) " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static String askUser() throws IOException {

        System.out.println("\nPlease, write number (more than 3 symbols):");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String answer = buffer.readLine();

        return answer.trim();
    }

    private static boolean isPalindrome(String number) {
        String reversedNumber = (new StringBuilder(number)).reverse().toString();
        String uniquePart = number.substring(0, number.length() / 2);

        return (uniquePart.equals(reversedNumber.substring(0, reversedNumber.length() / 2)));
    }

}
