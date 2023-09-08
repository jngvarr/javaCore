package ru.gb;

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
    private static int superAIhasMove = 0;
    private static boolean hasMove;


    public static void main(String[] args) {
        field = new char[3][];

        while (true) {
            fieldSizeChoice();
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkGameState(HUMAN_SIGN, "Вы победили!"))
                    break;
                superAITurn();
//                aiTurn();
                printField();
                if (checkGameState(AI_SIGN, "Победил компьютер!"))
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
//                break;
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
//        firstAITurn = true;
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
                System.out.print("Введите координату хода X (от 1 до " + fieldSizeX + "): ");
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
                System.out.print("Введите координату хода Y (от 1 до " + fieldSizeY + "): ");
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
        if (signCount(c, winCount)) {
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
     * Подсчет значков в линии
     *
     * @param c
     * @return
     */
    private static boolean signCount(char c, int wins) {
        ArrayList<Boolean> checks = new ArrayList<>();
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
//                if (checks.contains(true)) return true;
                if (field[i][j] == c) {
                    if (i + winCount <= fieldSizeX) {
                        checks.add(straightCheck(i, j, c, true, wins));
                    }
                    if (j + winCount <= fieldSizeY) {
                        checks.add(straightCheck(i, j, c, false, wins));
                    }
                    if (i + winCount <= fieldSizeX && j + winCount <= fieldSizeY) {
                        checks.add(diagonalCheck(i, j, c, true, wins));
                    }
                    if (j + winCount <= fieldSizeX && j - winCount <= fieldSizeY - winCount) {
                        checks.add(diagonalCheck(i, j, c, false, wins));
                    }
                }
            }
        }
        dots.clear();
        return checks.contains(true);
    }

    public static void blockStep(int x, int y) {
        if (isCellEmpty(x, y)) {
            field[x][y] = AI_SIGN;
        } else if (isCellValid(x - 1, y - 1) && isCellEmpty(x - 1, y - 1)) {
            field[x - 1 - dots.size()][y - 1 - dots.size()] = AI_SIGN;

            hasMove = true;
        }
//        dots.clear();
    }

    private static void tryToBlock(char c, int wins) {
        hasMove = false;
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (hasMove) break;
                if (field[i][j] == c && !hasMove) {

                    if (straightCheck(i, j, c, true, wins)) {
                        blockStep(dots.get(0).x + dots.size(), dots.get(0).y);

                    }
                    if (!hasMove) {
                        if (straightCheck(i, j, c, false, wins)) {
                            blockStep(dots.get(0).x, dots.get(0).y + dots.size());
                        }
                    }

                    if (!hasMove) {
                        if (diagonalCheck(i, j, c, true, wins)) {
                            blockStep(dots.get(0).x + dots.size(), dots.get(0).y + dots.size());
                        }
                    }
                    if (!hasMove) {
                        if (diagonalCheck(i, j, c, false, wins)) {
                            blockStep(dots.get(0).x + dots.size(), dots.get(0).y - dots.size());
                        }
                    }
                }
            }
        }
    }

    public static void superAITurn() {
        sAITurn = true;
        if (signCount(HUMAN_SIGN, winCount - 1)) tryToBlock(HUMAN_SIGN, winCount - 1);
        else tryToBlock(HUMAN_SIGN, winCount - 2);
        sAITurn = false;
        if (!hasMove) {
            aiTurn();
        }
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
    public static boolean straightCheck(int i, int j, char c, boolean straightDestination, int wins) {
        dots.clear();
        int check = 0;
        int edge = straightDestination ? fieldSizeX - 1 - i : fieldSizeY - 1 - j;
        for (int k = 0; k < edge; k++) {
            if (field[i][j] == c) {
                dots.add(new IntPair(i, j));
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
    public static boolean diagonalCheck(int i, int j, char c, boolean straightDestination, int wins) {
        dots.clear();
        int check = 0;
        int edge = straightDestination ? fieldSizeX - 1 - i : fieldSizeY - 1 - j;
        for (int k = 0; k < edge; k++) {
            if (field[i][j] == c) {
                dots.add(new IntPair(i, j));
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
        return check == wins;
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

