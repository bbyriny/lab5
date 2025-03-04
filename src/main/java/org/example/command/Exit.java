package org.example.command;

import org.example.manager.CommandManager;
import org.example.utils.RunMode;

/**
 * Команда exit
 */
public class Exit extends Command {
    public Exit(CommandManager commandManager) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
    }

    @Override
    public void run(String[] args) {
        stream.print("Программа успешно завершена\n");
        commandManager.setRunMode(RunMode.EXIT);
    }
}
