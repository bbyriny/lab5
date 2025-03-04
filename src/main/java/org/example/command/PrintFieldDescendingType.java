package org.example.command;

import org.example.manager.CommandManager;
import org.example.object.Dragon;
import org.example.object.DragonType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Команда print_field_descending_type
 */
public class PrintFieldDescendingType extends Command {
    public PrintFieldDescendingType(CommandManager commandManager) {
        super("print_field_descending_type", "вывести значения поля type всех элементов в порядке убывания");
        this.commandManager = commandManager;
        stream = commandManager.getStreamManager();
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        List<DragonType> types = new ArrayList<>();
        for (Dragon dragon : collectionManager.getCollection()) {
            types.add(dragon.getType());
        }
        types.sort(new DragonTypeComparator());
        types = types.reversed();
        stream.print("Значения поля type в порядке убывания:\n");
        stream.print(types.toString() + "\n");
    }

    private static class DragonTypeComparator implements Comparator<DragonType> {
        @Override
        public int compare(DragonType o1, DragonType o2) {
            return o1.compareTo(o2);
        }
    }
}
