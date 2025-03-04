package org.example.command;

import org.example.manager.CommandManager;

/**
 * Команда info
 */
public class Info extends Command {
    public Info(CommandManager commandManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        stream.print("$ Информация про коллекцию:\n");
        stream.print("Тип коллекции: " + collectionManager.getCollectionType().getName() + "\n");
        stream.print("Дата создания: " + collectionManager.getCreationTime() + "\n");
        stream.print("Размер коллекции: " + collectionManager.getCollectionSize() + "\n");
    }
}
