package org.example.command;

import org.example.manager.CommandManager;
import org.example.object.Dragon;

import java.util.HashSet;

/**
 * Команда show
 */
public class Show extends Command {
    public Show(CommandManager commandManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        if (collectionManager.isEmpty()) {
            stream.print("Коллекция пуста\n");
            return;
        }
        HashSet<Dragon> dragons = collectionManager.getCollection();
        stream.print("Элементы коллекции:\n");
        int count = 1;
        for (Dragon dragon : dragons) {
            stream.print(count++ + "-ый элемент:\n");
            stream.print(dragon.toString() + "\n");
        }
    }
}
