package org.example.command;

import org.example.manager.CommandManager;
import org.example.manager.RunManager;
import org.example.utils.InputFormat;

import java.io.*;

/**
 * Команда execute_script
 */
public class ExecuteScript extends Command {

    public ExecuteScript(CommandManager commandManager) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        if (args.length != 2) {
            stream.print("Неверный формат команды\n");
            return;
        }
        if (RunManager.usedScripts.contains(args[1])) {
            stream.print("Запуск скрипта " + args[1] + " вызывает рекурсию\n");
            System.exit(0);
        }
        RunManager.usedScripts.add(args[1]);
        try (FileInputStream fis = new FileInputStream(args[1])) {
            stream.print("Выполнение скрипта " + args[1] + ":\n");
            RunManager scriptRunner = new RunManager(fis, stream, InputFormat.FILE, collectionManager);
            scriptRunner.run();
        } catch (FileNotFoundException e) {
            stream.print("Файл со скриптом не найден\n");
            return;
        } catch (NullPointerException e) {
            stream.print("\nВ скрипте отсутствует команда exit. Программа завершена\n");
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        RunManager.usedScripts.remove(args[1]);
    }
}
