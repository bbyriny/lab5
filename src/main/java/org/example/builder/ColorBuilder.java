package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.Color;
import org.example.utils.InputFormat;

import java.util.Arrays;

/**
 * Класс билдер цвета
 */
public class ColorBuilder extends Builder {
    public ColorBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public Color build() {
        return readColor();
    }

    /**
     * Считывает цвет
     *
     * @return считанный цвет
     */
    private Color readColor() {
        while (true) {
            stream.print("$ Введите цвет " + Arrays.toString(Color.values()) + ":\n");
            String line = readLine();
            if (line.isEmpty()) return null;
            Color color;
            try {
                color = Color.valueOf(line.toUpperCase());
            } catch (IllegalArgumentException e) {
                stream.print("Введенный цвет не является одним из предложенных\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return color;
        }
    }
}
