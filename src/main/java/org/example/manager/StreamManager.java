package org.example.manager;

import java.io.PrintStream;

/**
 * Класс для единообразного управления потоками вывода
 */
public class StreamManager {
    private final PrintStream stream;

    public StreamManager(PrintStream stream) {
        this.stream = stream;
    }

    /**
     * Выводит сообщение
     *
     * @param msg сообщение
     */
    public void print(String msg) {
        stream.print(msg);
    }
}
