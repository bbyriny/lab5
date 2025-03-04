package org.example.command;

import org.example.builder.DragonBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда add_if_min
 */
public class AddIfMin extends Command {
    public AddIfMin(CommandManager commandManager) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Dragon dragon = new DragonBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
        Dragon min = collectionManager.findMin();
        if (min != null) {
            if (dragon.compareTo(min) > 0) {
                stream.print("Элемент не является минимальным\n");
                return;
            }
        }
        collectionManager.add(dragon);
        stream.print("Элемент успешно добавлен\n");
    }
}
