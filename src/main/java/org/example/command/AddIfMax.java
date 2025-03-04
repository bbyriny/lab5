package org.example.command;

import org.example.builder.DragonBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда add_if_max
 */
public class AddIfMax extends Command {
    public AddIfMax(CommandManager commandManager) {
        super("add_if_max {element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Dragon dragon = new DragonBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
        Dragon max = collectionManager.findMax();
        if (max != null) {
            if (dragon.compareTo(max) < 0) {
                stream.print("Элемент не является максимальным\n");
                return;
            }
        }
        collectionManager.add(dragon);
        stream.print("Элемент успешно добавлен\n");
    }
}
