package ru.gb;

import java.io.File;

public class Tree {

    public static void main(String[] args) {

        print(new File("."), "", true);

    }

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     *
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());


        File[] files = file.listFiles();
        if (files == null)
            return;

        int filesTotal = 0;
        int subDirTotal = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                subDirTotal++;
            } else filesTotal++;
        }

        int filesCounter = 0;
        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirCounter++;
                print(files[i], indent, subDirCounter == subDirTotal && filesCounter == filesTotal);
            } else {
                filesCounter++;
                print(files[i], indent, filesTotal == filesCounter && subDirCounter == subDirTotal);
            }
        }

    }

}
