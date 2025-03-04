package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.DragonCharacter;
import org.example.utils.InputFormat;

import java.util.Arrays;

/**
 * Класс билдер характера дракона
 */
public class DragonCharacterBuilder extends Builder {
    public DragonCharacterBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public DragonCharacter build() {
        return readDragonCharacter();
    }

    /**
     * Считывает характер
     *
     * @return считанный характер
     */
    private DragonCharacter readDragonCharacter() {
        while (true) {
            stream.print("$ Введите характер " + Arrays.toString(DragonCharacter.values()) + ":\n");
            String line = readLine();
            if (line.isEmpty()) {
                stream.print("Характер не может быть пустым\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            DragonCharacter dragonCharacter;
            try {
                dragonCharacter = DragonCharacter.valueOf(line.toUpperCase());
            } catch (IllegalArgumentException e) {
                stream.print("Введенный характер не является одним из предложенных\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return dragonCharacter;
        }
    }
}
