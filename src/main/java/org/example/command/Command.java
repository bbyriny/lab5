package org.example.command;

import org.example.manager.CollectionManager;
import org.example.manager.CommandManager;
import org.example.manager.StreamManager;

/**
 * Класс команда
 */
public abstract class Command {
    private final String name;
    private final String description;

    protected CommandManager commandManager;
    protected StreamManager stream;
    protected CollectionManager collectionManager;

    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Запускает команду
     */
    public abstract void run(String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
