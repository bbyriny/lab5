package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.utils.InputFormat;

/**
 * Класс билдер
 */
public abstract class Builder {
    protected InputFormat inputFormat;

    protected StreamManager stream;
    protected ScannerManager scanner;

    public Builder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        this.stream = stream;
        this.scanner = scanner;
        this.inputFormat = inputFormat;
    }

    /**
     * Заполняет объект данными, используя ввод
     *
     * @return Заполненный объект
     */
    abstract public Object build();


    /**
     * Выводит сообщение при вводе из файла
     *
     * @param msg сообщение
     */
    protected void printIfFileMode(String msg) {
        if (inputFormat == InputFormat.FILE) {
            stream.print(msg + "\n");
        }
    }

    /**
     * Резко завершает программу, если стоит файловый режим ввода. Нужно при возникновении ошибок во время ввода
     */
    protected void exitIfFileMode() {
        if (inputFormat == InputFormat.FILE) {
            System.exit(0);
        }
    }

    /**
     * Считывает строку из потока ввода
     *
     * @return считанная строка
     */
    protected String readLine() {
        stream.print("> ");
        String line = scanner.nextLine().trim();
        printIfFileMode(line);
        return line;
    }
}
