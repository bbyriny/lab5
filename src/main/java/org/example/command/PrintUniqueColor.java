package org.example.command;

import org.example.manager.CommandManager;
import org.example.object.Color;
import org.example.object.Dragon;

import java.util.HashSet;

/**
 * Команда print_unique_color
 */
public class PrintUniqueColor extends Command {
    public PrintUniqueColor(CommandManager commandManager) {
        super("print_unique_color", "вывести уникальные значения поля color всех элементов в коллекции");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        HashSet<Color> colors = new HashSet<>();
        for (Dragon dragon : collectionManager.getCollection()) {
            colors.add(dragon.getColor());
        }
        stream.print("Уникальные значения color:\n");
        stream.print(colors.toString() + "\n");
    }
}
