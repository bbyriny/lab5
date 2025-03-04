package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.Coordinates;
import org.example.utils.InputFormat;

/**
 * Класс билдер координат
 */
public class CoordinatesBuilder extends Builder {
    public CoordinatesBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public Coordinates build() {
        return new Coordinates(
                readX(),
                readY()
        );
    }

    /**
     * Считывает координату x
     *
     * @return считанная координата
     */
    private int readX() {
        while (true) {
            stream.print("$ Введите координату x:\n");
            int x;
            try {
                x = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                stream.print("Координата x должна быть целым числом\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return x;
        }
    }

    /**
     * Считывает координату y
     *
     * @return считанная координата
     */
    private double readY() {
        while (true) {
            stream.print("$ Введите координату y:\n");
            double y;
            try {
                y = Double.parseDouble(readLine());
            } catch (NumberFormatException e) {
                stream.print("Координата y должна быть целым числом или вещественным числом\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return y;
        }
    }
}
