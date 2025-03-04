package org.example.builder;

import org.example.manager.ScannerManager;
import org.example.manager.StreamManager;
import org.example.object.*;
import org.example.utils.InputFormat;

import java.time.ZonedDateTime;

/**
 * Класс билдер дракона
 */
public class DragonBuilder extends Builder {
    public DragonBuilder(StreamManager stream, ScannerManager scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public Dragon build() {
        return new Dragon(
                readName(),
                readCoordinates(),
                ZonedDateTime.now(),
                readAge(),
                readColor(),
                readDragonType(),
                readDragonCharacter(),
                readDragonCave()
        );
    }

    /**
     * Считывает имя
     *
     * @return считанное имя
     */
    private String readName() {
        while (true) {
            stream.print("$ Введите имя дракона:\n");
            String name = readLine();
            if (name.isEmpty()) {
                stream.print("Название не должно быть пустым\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return name;
        }
    }

    /**
     * Считывает координаты
     *
     * @return считанные координаты
     */
    private Coordinates readCoordinates() {
        stream.print("$ Ввод координат\n");
        return new CoordinatesBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Считывает возраст
     *
     * @return считанный возраст
     */
    private int readAge() {
        while (true) {
            stream.print("$ Введите возраст:\n");
            int age;
            try {
                age = Integer.parseInt(readLine());
            } catch (NumberFormatException e) {
                stream.print("Возраст должен быть целым числом\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            if (age <= 0) {
                stream.print("Возраст должен быть больше 0\n");
                exitIfFileMode();
                stream.print("Повторная попытка ввода\n");
                continue;
            }
            return age;
        }
    }

    /**
     * Считывает цвет
     *
     * @return считанный цвет
     */
    private Color readColor() {
        return new ColorBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Считывает тип дракона
     *
     * @return считанный тип
     */
    private DragonType readDragonType() {
        return new DragonTypeBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Считывает характер
     *
     * @return считанный характер
     */
    private DragonCharacter readDragonCharacter() {
        return new DragonCharacterBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Считывает пещеру
     *
     * @return считанная пещера
     */
    private DragonCave readDragonCave() {
        stream.print("$ Ввод пещеры\n");
        return new DragonCaveBuilder(stream, scanner, inputFormat).build();
    }
}
