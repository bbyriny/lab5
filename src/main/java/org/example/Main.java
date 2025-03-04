package org.example;

import org.example.manager.CollectionManager;
import org.example.manager.RunManager;
import org.example.manager.StreamManager;
import org.example.utils.InputFormat;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите имя загружаемого файла (например, example.json) как аргумент командной строки\n");
            System.exit(1);
        }
        String filename = args[0];
        StreamManager stream = new StreamManager(System.out);
        CollectionManager collectionManager = new CollectionManager(stream, filename);
        RunManager runManager = new RunManager(System.in, stream, InputFormat.STANDARD, collectionManager);
        runManager.run();
    }
}