package org.example.command;

import org.example.builder.DragonBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда remove_lower
 */
public class RemoveLower extends Command {
    public RemoveLower(CommandManager commandManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Dragon dragon = new DragonBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
        stream.print("Удалено " + collectionManager.removeLower(dragon) + " элементов\n");
    }
}
