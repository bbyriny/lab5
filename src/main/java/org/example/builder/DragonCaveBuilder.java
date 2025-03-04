package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.DragonCave;
import org.example.utils.InputFormat;

/**
 * Класс билдер пещеры дракона
 */
public class DragonCaveBuilder extends Builder {
    public DragonCaveBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public DragonCave build() {
        return new DragonCave(
                readDepth(),
                readNumberOfTreasures()
        );
    }

    /**
     * Считывает глубину
     *
     * @return считанная глубина
     */
    private double readDepth() {
        while (true) {
            stream.print("$ Введите глубину:\n");
            double depth;
            try {
                depth = Double.parseDouble(readLine());
            } catch (NumberFormatException e) {
                stream.print("Глубина должна быть целым или вещественным числом\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return depth;
        }
    }

    /**
     * Считывает количество сокровищ
     *
     * @return считанное количество
     */
    private float readNumberOfTreasures() {
        while (true) {
            stream.print("$ Введите количество сокровищ:\n");
            float numberOfTreasures;
            try {
                numberOfTreasures = Float.parseFloat(readLine());
            } catch (NumberFormatException e) {
                stream.print("Количество сокровищ должно быть целым или вещественным числом\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            if (numberOfTreasures <= 0) {
                stream.print("Количество сокровищ должно быть больше 0\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return numberOfTreasures;
        }
    }
}
