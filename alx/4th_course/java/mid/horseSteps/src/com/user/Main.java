package com.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    // GRID

    final private static int ROWS = 7;
    final private static int COLUMNS = 9;
    final private static char UNREACHED = '-';
    final private static char REACHED = 'x';

    // private properties

    private static int[][] accessibility = new int[ROWS][COLUMNS];
    private static char[][] board = new char[ROWS][COLUMNS];

    // step map
    private static int[] horizontal = { 2, 1, -1, -2, -2, -1, 1, 2 };
    private static int[] vertical   = { -1, -2, -2, -1, 1, 2, 2, 1 };


    public static void main(String[] args) {

        // setup clear board
        fillBoardWith(UNREACHED);

        // get max step from user
        int maxSteps = getMaxSteps();

        // setup barriers
        setupAccessebility();

        // run main program
        runHorse(maxSteps);

        // print result
        print(board);
    }


    private static void print(char[][] position) {
        System.out.println();

        for (int idx_k = 0; idx_k < ROWS; idx_k++) {
            for (int idx_s = 0; idx_s < COLUMNS; idx_s++) {
                System.out.print(position[idx_k][idx_s] + "  ");
            }
            System.out.println();
            System.out.println();
        }

        System.out.println();
    }

    private static void setupAccessebility() {
        int accessCount = 0, x = 0, y = 0;

        for (int idx_x = 0; idx_x < ROWS; idx_x++) {
            for (int idx_y = 0; idx_y < COLUMNS; idx_y++) {
                accessCount = 0;
                for (int idx_i = 0; idx_i < 8; idx_i++) {
                    x = idx_x + horizontal[idx_i];
                    y = idx_y + vertical[idx_i];

                    if (isInsideBoard(x, y)) accessCount++;
                }

                accessibility[idx_x][idx_y] = accessCount;
            }
        }
    }

    private static void runHorse(int maxSteps) {
        int isStepDenied = 0, x_min = 0, y_min = 0, x = 0, y = 0, x_raw = 0, y_raw = 0;
        board[x_raw][y_raw] = REACHED;

        for (int stepNumber = 1; stepNumber <= maxSteps; stepNumber++) {
            isStepDenied = 1;

            for (int idx_i = 0; idx_i < 8; idx_i++) {
                x = x_raw + horizontal[idx_i];
                y = y_raw + vertical[idx_i];

                if (isInsideBoard(x, y) && (board[x][y] == UNREACHED)) {
                    accessibility[x][y]--;
                    if (isStepDenied == 1) {
                        x_min = x;
                        y_min = y;
                        isStepDenied = 0;
                    }

                    if (accessibility[x][y] < accessibility[x_min][y_min]) {
                        x_min = x;
                        y_min = y;
                    }
                }
            }

            if (isStepDenied == 1) break;

            x_raw = x_min;
            y_raw = y_min;
            board[x_raw][y_raw] = REACHED;
        }
    }


    // support methods

    private static boolean isInsideBoard(int x, int y) {
        return (0 <= x) && (x < ROWS) && (0 <= y) && (y < COLUMNS);
    }

    private static void fillBoardWith(char symbol) {
        for (int idx_i = 0; idx_i < ROWS; idx_i++) {
            for (int idx_j = 0; idx_j < COLUMNS; idx_j++) {
                board[idx_i][idx_j] = symbol;
            }
        }
    }

    private static int getMaxSteps() {
        System.out.print("Please, write max available step amount for horse: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxSteps = 0;
        try {
            maxSteps = Integer.valueOf(br.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }

        return maxSteps;
    }

}
