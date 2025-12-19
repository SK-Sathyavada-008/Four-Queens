package Projects;

import java.util.Scanner;
import java.lang.Math;

public class FourQueens {

    // Method to check same row 
    public static boolean rowCheck(int r1, int c1, int r2, int c2) {
        if (r1 == r2)
            return true;
        else
            return false;
    }
    // Method to check same column 
    public static boolean columnCheck(int r1, int c1, int r2, int c2) {
        if (c1 == c2)
            return true;
        else
            return false;
    }
    // Method to check same diagonal 
    public static boolean diagonalCheck(int r1, int c1, int r2, int c2) {
        int rowDifference = Math.abs(r1 - r2);
        int colDifference = Math.abs(c1 - c2);
        if (rowDifference == colDifference)
            return true;
        else
            return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // declaring variables for rows and columns
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, c1 = 0, c2 = 0, c3 = 0, c4 = 0;

        // Game Start - Title and Instructions
        System.out.println("-----------------------");
        System.out.println(" 👸👑 FOUR QUEENS 👑👸");
        System.out.println("-----------------------");
        System.out.println();
        System.out.println("Instructions : ");
        System.out.println("------------");
        System.out.println("1. The 4 Queens Problem consists in placing four queens on a 4 x 4 chessboard \n2. No two queens attack each other. \n3. That is, no two queens are allowed to be placed on the same row, the same column or the same diagonal.");
        System.out.println();
        System.out.println();
        System.out.println("Let's get started 🏴 ");
        System.out.println();

        // Lives 
        int lives = 3;
        System.out.print("\t\tLives Left : ");
        for (int h = 1; h <= lives; h++) {
            System.out.print("♥️ ");
        }

        // Arrays for failed cases 
        int[] failedRow = new int[3];
        int[] failedCol = new int[3];
        int failCount = 0; // Tracks the number of failures stored in the arrays

        // First board
        System.out.println();
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("⚫ ");
            }
            System.out.println();
        }

        // Placing the first queen 
        System.out.println();
        System.out.print("Enter row number to place first queen : ");
        r1 = sc.nextInt();
        System.out.print("Enter column number to place first queen : ");
        c1 = sc.nextInt();
        if (r1 <= 0 || r1 > 4 || c1 <= 0 || c1 > 4) {
            System.out.println("Invalid row/column numbers");
        } else {
            System.out.println();
            System.out.print("\t\tLives Left : ");
            for (int h = 1; h <= lives; h++) {
                System.out.print("♥️ ");
            }
            System.out.println();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (i == (r1 - 1) && j == (c1 - 1))
                        System.out.print("👑 ");
                    else
                        System.out.print("⚫ ");
                }
                System.out.println();
            }
        }



        // Placing second Queen 
        boolean twocorrect = false;
        while (twocorrect == false && lives > 0) {
            System.out.println();
            System.out.print("Enter row number to place second queen : ");
            r2 = sc.nextInt();
            System.out.print("Enter column number to place second queen : ");
            c2 = sc.nextInt();
            // check if position is valid - no penalty
            if (r2 <= 0 || r2 > 4 || c2 <= 0 || c2 > 4) {
                System.out.println("Invalid row/column numbers. Please try again.");
            }
            // same spot as first queen  - lose life
            else if (r2 == r1 && c2 == c1) {
                System.out.println("You can't place the second queen on the first queen!");
                failedRow[failCount] = r2;
                failedCol[failCount] = c2;
                failCount++;
                lives--;
                twocorrect = false;
            }
            // position check - row, column, diagonal - lose life
            else if (rowCheck(r1, c1, r2, c2) == true || columnCheck(r1, c1, r2, c2) == true || diagonalCheck(r1, c1, r2, c2) == true) {
                System.out.println("\nIncorrect Position!");
                failedRow[failCount] = r2;
                failedCol[failCount] = c2;
                failCount++;
                lives--;
                twocorrect = false;
                System.out.println();
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                // Board display with fail attempts
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        boolean isFailedSpot = false;
                        for (int k = 0; k < failCount; k++) {
                            if ((i + 1) == failedRow[k] && (j + 1) == failedCol[k]) {
                                isFailedSpot = true;
                                break;
                            }
                        }
                        if (i == (r1 - 1) && j == (c1 - 1)) {
                            System.out.print("👑 "); // first queen
                        }
                        else if (isFailedSpot) {
                            System.out.print("❌ "); // wrong position
                        }
                        else {
                            System.out.print("⚫ "); // unused spots 
                        }
                    }
                    System.out.println();
                }
            }
            // correct position 
            else {
                twocorrect = true;
                System.out.println();
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == (r1 - 1) && j == (c1 - 1))
                            System.out.print("👑 ");
                        else if (i == (r2 - 1) && j == (c2 - 1))
                            System.out.print("👑 ");
                        else
                            System.out.print("⚫ ");
                    }
                    System.out.println();
                }
            }
        }
        // Lost game case 
        if (twocorrect == false && lives == 0) {
            System.out.println("\n\t\t❌ GAME OVER ❌");
            System.out.println("😭 You ran out of lives. 😭 You failed to place the second queen safely.");
        }



        // Placing third Queen 
        boolean threecorrect = false; 
        while (threecorrect == false && lives > 0) {
            System.out.println();
            System.out.print("Enter row number to place third queen : ");
            r3 = sc.nextInt();
            System.out.print("Enter column number to place third queen : ");
            c3 = sc.nextInt();

            // check if position is valid - no penalty
            if (r3 <= 0 || r3 > 4 || c3 <= 0 || c3 > 4) {
                System.out.println("Invalid row/column numbers. Please try again.");
            }
            // same spot as first queen or second queen  - lose life
            else if ((r3 == r1 && c3 == c1) || (r3 == r2 && c3 == c2)) {
                System.out.println("You can't place the third queen on another queen!");
                failedRow[failCount] = r3;
                failedCol[failCount] = c3;
                failCount++;
                lives--;
                threecorrect = false;
            }
            else if ((rowCheck(r1, c1, r3, c3) == true || columnCheck(r1, c1, r3, c3) == true || diagonalCheck(r1, c1, r3, c3) == true) || (rowCheck(r2, c2, r3, c3) == true || columnCheck(r2, c2, r3, c3) == true || diagonalCheck(r2, c2, r3, c3) == true)) {
                System.out.println("\nIncorrect Position!");
                failedRow[failCount] = r3;
                failedCol[failCount] = c3;
                failCount++;
                lives--;
                threecorrect = false;
                System.out.println();
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                // Board display with fail attempts
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        boolean isFailedSpot = false;
                        for (int k = 0; k < failCount; k++) {
                            if ((i + 1) == failedRow[k] && (j + 1) == failedCol[k]) {
                                isFailedSpot = true;
                                break;
                            }
                        }
                        if (i == (r1 - 1) && j == (c1 - 1) || i == (r2 - 1) && j == (c2 - 1)) {
                            System.out.print("👑 "); // first and second queens
                        } else if (isFailedSpot) {
                            System.out.print("❌ "); // wrong position
                        } else {
                            System.out.print("⚫ "); // unused spots 
                        }
                    }
                    System.out.println();
                }
            }
            // correct position 
            else {
                threecorrect = true;
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == (r1 - 1) && j == (c1 - 1))
                            System.out.print("👑 ");
                        else if (i == (r2 - 1) && j == (c2 - 1))
                            System.out.print("👑 ");
                        else if (i == (r3 - 1) && j == (c3 - 1))
                            System.out.print("👑 ");
                        else
                            System.out.print("⚫ ");
                    }
                    System.out.println();
                }
            }
        }
        if (twocorrect == true && threecorrect == false && lives == 0) {
            System.out.println("\n\t\t❌ GAME OVER ❌");
            System.out.println("😭 You ran out of lives. 😭 You failed to place the third queen safely.");
        }




        // Placing fourth Queen 
        boolean fourcorrect = false; 
        while (fourcorrect == false && lives > 0) {
            System.out.println();
            System.out.print("Enter row number to place fourth queen : ");
            r4 = sc.nextInt();
            System.out.print("Enter column number to place fourth queen : ");
            c4 = sc.nextInt();
            // check if position is valid - no penalty
            if (r4 <= 0 || r4 > 4 || c4 <= 0 || c4 > 4) {
                System.out.println("Invalid row/column numbers. Please try again.");
            }
            // same spot as previous queens - lose life
            else if ((r4 == r1 && c4 == c1) || (r4 == r2 && c4 == c2) || (r4 == r3 && c4 == c3)) {
                System.out.println("You can't place the fourth queen on another queen!");
                failedRow[failCount] = r4;
                failedCol[failCount] = c4;
                failCount++;
                lives--;
                fourcorrect = false;
            }
            // position check - row, column, diagonal - lose life
            else if ((rowCheck(r1, c1, r4, c4) == true || columnCheck(r1, c1, r4, c4) == true || diagonalCheck(r1, c1, r4, c4) == true) ||
                (rowCheck(r2, c2, r4, c4) == true || columnCheck(r2, c2, r4, c4) == true || diagonalCheck(r2, c2, r4, c4) == true) ||
                (rowCheck(r3, c3, r4, c4) == true || columnCheck(r3, c3, r4, c4) == true || diagonalCheck(r3, c3, r4, c4) == true)) {
                System.out.println("\nIncorrect Position!");
                failedRow[failCount] = r4;
                failedCol[failCount] = c4;
                failCount++;
                lives--;
                fourcorrect = false;
                System.out.println();
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                // Board display with fail attempts
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        boolean isFailedSpot = false;
                        for (int k = 0; k < failCount; k++) {
                            if ((i + 1) == failedRow[k] && (j + 1) == failedCol[k]) {
                                isFailedSpot = true;
                                break;
                            }
                        }
                        if (i == (r1 - 1) && j == (c1 - 1) || i == (r2 - 1) && j == (c2 - 1) || i == (r3 - 1) && j == (c3 - 1)) {
                            System.out.print("👑 "); // first, second, and third queens
                        } else if (isFailedSpot) {
                            System.out.print("❌ "); // wrong position
                        } else {
                            System.out.print("⚫ "); // unused spots 
                        }
                    }
                    System.out.println();
                }
            }
            // correct position 
            else {
                fourcorrect = true; // Success!
                System.out.println("\n🎉 VICTORY! You have placed all four queens safely!");
                System.out.print("\t\tLives Left : ");
                for (int h = 1; h <= lives; h++) {
                    System.out.print("♥️ ");
                }
                System.out.println();
                // Board display with all four correct queens (r1, c1, r2, c2, r3, c3, r4, c4)
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == (r1 - 1) && j == (c1 - 1))
                            System.out.print("👑 ");
                        else if (i == (r2 - 1) && j == (c2 - 1))
                            System.out.print("👑 ");
                        else if (i == (r3 - 1) && j == (c3 - 1))
                            System.out.print("👑 ");
                        else if (i == (r4 - 1) && j == (c4 - 1))
                            System.out.print("👑 ");
                        else
                            System.out.print("⚫ ");
                    }
                    System.out.println();
                }
            }
        }
        // Game Over check for the fourth queen
        if (threecorrect==true && twocorrect==true && fourcorrect == false && lives == 0) {
            System.out.println("\n\t\t❌ GAME OVER ❌");
            System.out.println("😭 You ran out of lives 😭. You failed to place the fourth queen safely.");
        }

        sc.close();
    }
}