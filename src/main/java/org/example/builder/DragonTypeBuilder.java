package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.DragonType;
import org.example.utils.InputFormat;

import java.util.Arrays;

/**
 * Класс билдер типа дракона
 */
public class DragonTypeBuilder extends Builder {
    public DragonTypeBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public DragonType build() {
        return readDragonType();
    }

    /**
     * Считывает тип
     *
     * @return считанный тип
     */
    private DragonType readDragonType() {
        while (true) {
            stream.print("$ Введите тип " + Arrays.toString(DragonType.values()) + ":\n");
            String line = readLine();
            if (line.isEmpty()) {
                stream.print("Тип не может быть пустым\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            DragonType type;
            try {
                type = DragonType.valueOf(line.toUpperCase());
            } catch (IllegalArgumentException e) {
                stream.print("Введенный тип не является одним из предложенных\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return type;
        }
    }
}
