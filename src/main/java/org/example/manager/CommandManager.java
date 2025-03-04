package org.example.manager;

import org.example.command.*;
import org.example.utils.RunMode;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для хранения команд
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();
    private final StreamManager streamManager;
    private final ScannerManager scanner;
    private final CollectionManager collectionManager;
    private final RunManager runManager;

    public CommandManager(StreamManager streamManager, ScannerManager scannerManager, RunManager runManager, CollectionManager collectionManager) {
        this.streamManager = streamManager;
        this.scanner = scannerManager;
        this.collectionManager = collectionManager;
        this.runManager = runManager;

        initCommands();
    }

    private void initCommands() {
        addCommand("help", new Help(this));
        addCommand("info", new Info(this));
        addCommand("show", new Show(this));
        addCommand("add", new Add(this));
        addCommand("update", new Update(this));
        addCommand("remove_by_id", new RemoveById(this));
        addCommand("clear", new Clear(this));
        addCommand("save", new Save(this));
        addCommand("execute_script", new ExecuteScript(this));
        addCommand("exit", new Exit(this));
        addCommand("add_if_max", new AddIfMax(this));
        addCommand("add_if_min", new AddIfMin(this));
        addCommand("remove_lower", new RemoveLower(this));
        addCommand("filter_by_type", new FilterByType(this));
        addCommand("print_unique_color", new PrintUniqueColor(this));
        addCommand("print_field_descending_type", new PrintFieldDescendingType(this));
    }

    /**
     * Добавляет команду
     * @param name имя команды, по которому будет доступ для вызова
     * @param command команда
     */
    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public StreamManager getStreamManager() {
        return streamManager;
    }

    public ScannerManager getScanner() {
        return scanner;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public RunManager getRunManager() {
        return runManager;
    }

    public void setRunMode(RunMode runMode) {
        runManager.setRunMode(runMode);
    }
}
