package org.example.command;

import org.example.builder.DragonBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда add
 */
public class Add extends Command {
    public Add(CommandManager commandManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Dragon dragon = new DragonBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
        collectionManager.add(dragon);
        stream.print("Успешно добавлено\n");
    }
}
