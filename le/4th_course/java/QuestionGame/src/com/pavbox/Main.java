package com.pavbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Небольшая игра по угадыванию буквы. Регистр автоматически приводится к большому.
 * Для безопасности при вводе нескольких символов берется только первый. Пробелы по краям удаляются.
 */

public class Main {

    private final static char SECRET = 'L';

    public static void main(String[] args) {
        try {
            do {
                Character answer = askUser();

                if (answer.equals(SECRET)) {
                    System.out.println("Your answer is Correct!");
                    break;
                } else if (answer.compareTo(SECRET) > 0) {
                    System.out.println("You're is too high.");
                } else {
                    System.out.println("You're is too low.");
                }

            } while (true);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static Character askUser() throws IOException {

        System.out.println("\nPlease, write char in A-Z ranges:");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String answer = buffer.readLine().toUpperCase();

        if (answer.length() > 1) {
            System.out.println("You wrote answer that has more length than requied. We will use only first char of string...");
        }

        return answer.trim().charAt(0);
    }
}
