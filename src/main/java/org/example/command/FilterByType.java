package org.example.command;

import org.example.builder.DragonTypeBuilder;
import org.example.manager.CommandManager;
import org.example.object.Dragon;
import org.example.object.DragonType;

/**
 * Команда filter_by_type
 */
public class FilterByType extends Command {

    public FilterByType(CommandManager commandManager) {
        super("filter_by_type type", "вывести элементы, значение поля type которых равно заданному");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        DragonType dragonType;
        if (args.length >= 2) {
            try {
                dragonType = DragonType.valueOf(args[1].toUpperCase());
            } catch (IllegalArgumentException e) {
                dragonType = new DragonTypeBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
            }
        } else {
            dragonType = new DragonTypeBuilder(stream, commandManager.getScanner(), commandManager.getRunManager().inputFormat).build();
        }
        boolean f = true;
        for (Dragon dragon : collectionManager.getCollection()) {
            if (dragon.getType() == dragonType) {
                f = false;
                stream.print(dragon.toString() + "\n");
            }
        }
        if (f) {
            stream.print("Элементов с заданным типом нет в коллекции\n");
        }
    }
}
