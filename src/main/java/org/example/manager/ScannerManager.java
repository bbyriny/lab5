package org.example.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс для единообразного управления потоками ввода
 */
public class ScannerManager {
    private final BufferedReader reader;

    public ScannerManager(InputStreamReader reader) {
        this.reader = new BufferedReader(reader);
    }

    /**
     * Читает новую строку
     *
     * @return считанная строка
     */
    public String nextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
