package org.example.manager;

import org.example.command.Command;
import org.example.utils.InputFormat;
import org.example.utils.RunMode;
import org.example.utils.ValidationError;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс для запуска программы
 */
public class RunManager {
    private final CommandManager commandManager;
    private final StreamManager stream;
    private final ScannerManager scanner;

    private final Map<String, Command> commands;

    private RunMode runMode = RunMode.RUN;
    public static List<String> usedScripts = new ArrayList<>();
    public InputFormat inputFormat;

    public RunManager(InputStream inputStream, StreamManager stream, InputFormat inputFormat, CollectionManager collectionManager) {
        this.inputFormat = inputFormat;
        this.stream = stream;
        scanner = new ScannerManager(new InputStreamReader(inputStream));
        commandManager = new CommandManager(stream, scanner, this, collectionManager);
        commands = commandManager.getCommands();
    }

    /**
     * Запуск
     */
    public void run() {
        while (runMode == RunMode.RUN) {
            stream.print("> ");
            String nextCommand = scanner.nextLine().trim();
            if (nextCommand.isEmpty()) {
                continue;
            }
            if (inputFormat == InputFormat.FILE) {
                stream.print(nextCommand + "\n");
            }
            String[] splitCommand = nextCommand.split(" ");
            try {
                commands.get(splitCommand[0]).run(splitCommand);
            } catch (ValidationError e) {
                throw e;
            } catch (NullPointerException e) {
                stream.print("Команда не распознана\n");
            } catch (Exception e) {
                stream.print("Неопознанная ошибка\n");
                System.exit(0);
            }
        }
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }
}
