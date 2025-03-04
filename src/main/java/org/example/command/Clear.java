package org.example.command;

import org.example.manager.CommandManager;

/**
 * Команда clear
 */
public class Clear extends Command {
    public Clear(CommandManager commandManager) {
        super("clear", "очистить коллекцию");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        collectionManager.clear();
        stream.print("Коллекция успешно очищена\n");
    }
}
