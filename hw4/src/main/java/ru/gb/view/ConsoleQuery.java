package ru.gb.view;

import java.util.Scanner;

public class ConsoleQuery {
    Scanner sc = new Scanner(System.in);

    public String consoleQuery() {
        System.out.println("Пожалуйста введите имя файла с исходными данными");
        return sc.nextLine();
    }
}
