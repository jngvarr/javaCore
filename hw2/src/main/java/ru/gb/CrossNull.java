package ru.gb;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CrossNull {
    private static int winCount; // Выигрышная комбинация

    public static final char HUMAN_SIGN = 'X';
    public static final char AI_SIGN = '0';
    public static final char EMPTY_POLE = '+';
    public static char[][] field;
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static boolean sAITurn = false;

    private static final Scanner sc = new Scanner(System.in);
    private static final Random rnd = new Random();
    private static ArrayList<IntPair> dots = new ArrayList<>();



    public static void main(String[] args) {
        field = new char[3][];

        while (true) {
            fieldSizeChoice();
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(HUMAN_SIGN, "Вы победили!")
                break;
                aiTurn();
                printField();
                if (checkGameState(AI_SIGN, "Победил компьютер!")
                break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if (!sc.next().equalsIgnoreCase("Y"))
                break;
        }
    }


    public static void fieldSizeChoice() {
        do {
            System.out.print("Введите размер поля по горизонтали (значение от 3 до 5): \n> ");
            if (sc.hasNextInt()) { // если кто-то смотрит код, здесь было так же как и с координатой У, но \n застревал и повторно игра не запускалась, не смог победить по другому
                fieldSizeX = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Некорректное число, повторите попытку ввода.");
                sc.nextLine();
            }

        } while (fieldSizeX < 3 || fieldSizeX > 5);
        do {
            System.out.print("Введите размер поля по вертикали (значение от 3 до 5): \n> ");
            fieldSizeY = Integer.parseInt(sc.nextLine());
        } while (fieldSizeY < 3 || fieldSizeY > 5);
    }

    public static void winCountDetermine() {
        if (Math.min(fieldSizeY, fieldSizeX) == 3) {
            winCount = 3;
        } else winCount = 4;
    }

    private static void initialize() {
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = EMPTY_POLE;
            }
        }
        winCountDetermine();
    }


    private static void printField() {
        System.out.print("+");
        for (int y = 0; y < fieldSizeX * 2 + 1; y++) {
            System.out.print((y % 2 == 0) ? "-" : y / 2 + 1);
        }
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int x = 0; x < fieldSizeX * 2 + 2; x++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        // sAITurn = false;
        do {

            while (true) {
                System.out.print("Введите координату хода X (от 1 до 3): ");
                if (sc.hasNextInt()) {
                    x = sc.nextInt() - 1;
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    sc.nextLine();
                }
            }

            while (true) {
                System.out.print("Введите координату хода Y (от 1 до 3): ");
                if (sc.hasNextInt()) {
                    y = sc.nextInt() - 1;
                    sc.nextLine();
                    break;
                } else {
                    System.out.println("Некорректное число, повторите попытку ввода.");
                    sc.nextLine();
                }
            }
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = HUMAN_SIGN;
    }

    /**
     * Проверка, ячейка является пустой (DOT_EMPTY)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellEmpty(int x, int y) {
        return field[x][y] == EMPTY_POLE;
    }

    /**
     * Проверка корректности ввода
     * (координаты хода не должны превышать размерность игрового поля)
     *
     * @param x
     * @param y
     * @return
     */
    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < fieldSizeY && y >= 0 && y < fieldSizeX;
    }

    /**
     * Обработка хода компьютера
     */
    private static void aiTurn() {
        int x, y;

        do {
            x = rnd.nextInt(fieldSizeX);
            y = rnd.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = AI_SIGN;
    }

    /**
     * Проверка состояния игры
     *
     * @param c фишка игрока
     * @param s победный слоган
     * @return
     */
    private static boolean checkGameState(char c, String s) {
        if (checkWin(c)) {
            System.out.println(s);
            return true;
        }
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }

        return false; // Игра продолжается
    }

    /**
     * Проверка победы
     *
     * @param c
     * @return
     */
    private static boolean checkWin(char c) {
        ArrayList<Boolean> checks = new ArrayList<>();
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {

                if (field[i][j] == c) {
                    if (i + winCount <= fieldSizeX) {
                        checks.add(straightCheck(i, j, c, true));
                    }
                    if (j + winCount <= fieldSizeY) {
                        checks.add(straightCheck(i, j, c, false));
                    }
                    if (i + winCount <= fieldSizeX && j + winCount <= fieldSizeY) {
                        checks.add(diagonalCheck(i, j, c, true));
                    }
                    if (i + winCount <= fieldSizeX && j >= winCount) {
                        checks.add(diagonalCheck(i, j, c, false));
                    }
                }
            }
        }
        return checks.contains(true);
    }

    public void superAITurn() {
        sAITurn = true;
        checkWin(HUMAN_SIGN);
        sAITurn = false;

    }

    /**
     * Проверка выйгрышной комбинации по горизонтали и вертикали
     *
     * @param i
     * @param j
     * @param c
     * @param straightDestination = true - горизонталь, false - вертикаль
     * @return
     */
    public static boolean straightCheck(int i, int j, char c, boolean straightDestination) {
        int wins = sAITurn ? winCount / 2 : winCount;
        int check = 0;
        for (int k = 0; k < wins; k++) {
            if (field[i][j] == c) {
                dots.add(new IntPair(i,j));
                check++;
                int i1 = straightDestination ? i++ : j++;
            }
        }
        return check == wins;
    }

    /**
     * Проверка выйгрышной комбинации по диагоналям
     *
     * @param i
     * @param j
     * @param c
     * @param straightDestination - true - левый верх - правый низ, false - левый низ - правый верх
     * @return
     */
    public static boolean diagonalCheck(int i, int j, char c, boolean straightDestination) {
        int wins = sAITurn ? winCount / 2 : winCount;
        int check = 0;
        for (int k = 0; k < winCount; k++) {
            if (field[i][j] == c) {
                dots.add(new IntPair(i,j));
                check++;
                if (straightDestination) {
                    i++;
                    j++;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return check == winCount;
    }

    /**
     * Проверка на ничью
     *
     * @return
     */
    private static boolean checkDraw() {
        for (int x = 0; x < fieldSizeY; x++) {
            for (int y = 0; y < fieldSizeX; y++) {
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

}

