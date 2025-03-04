package org.example.command;

import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда remove_by_id
 */
public class RemoveById extends Command {

    public RemoveById(CommandManager commandManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2) {
            stream.print("Неверный формат команды. Нет id\n");
            return;
        }
        long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            stream.print("id должен быть целым числом\n");
            return;
        }
        Dragon dragon = collectionManager.findById(id);
        if (dragon == null) {
            stream.print("Объекта под данным id не существует\n");
            return;
        }
        collectionManager.remove(dragon);
        stream.print("Успешно удалено\n");
    }
}
