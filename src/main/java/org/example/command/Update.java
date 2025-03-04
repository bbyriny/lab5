package org.example.command;

import org.example.builder.DragonBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;

/**
 * Команда update
 */
public class Update extends Command {

    public Update(CommandManager commandManager) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
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
        Dragon aim = collectionManager.findById(id);
        if (aim == null) {
            stream.print("Объекта под данным id не существует\n");
        } else {
            aim.update(new DragonBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build());
            stream.print("Замена прошла успешно\n");
        }
    }
}
