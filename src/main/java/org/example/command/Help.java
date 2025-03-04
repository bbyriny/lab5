package org.example.command;

import org.example.manager.CommandManager;

/**
 * Команда help
 */
public class Help extends Command {
    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
    }

    @Override
    public void run(String[] args) {
        stream.print("$ Справка по доступным командам:\n");
        for (Command command : commandManager.getCommands().values()) {
            stream.print(command.getName() + " : " + getDescription() + "\n");
        }
    }
}
