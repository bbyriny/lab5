package org.example.command;

import org.example.manager.CommandManager;
import org.example.manager.JsonManager;

/**
 * Команда save
 */
public class Save extends Command {
    public Save(CommandManager commandManager) {
        super("save", "сохранить коллекцию в файл");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
    }

    @Override
    public void run(String[] args) {
        JsonManager jsonManager = new JsonManager(commandManager.getStreamManager(), commandManager.getCollectionManager());
        jsonManager.writeJson();
        stream.print("Коллекция успешно сохранена в файл\n");
    }
}
