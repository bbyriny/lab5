package org.example.manager;

import org.example.object.Dragon;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Класс для хранения коллекции
 */
public class CollectionManager {
    private HashSet<Dragon> collection = new HashSet<>();
    private final java.time.ZonedDateTime creationTime;

    public final String fileName;

    public CollectionManager(StreamManager stream, String fileName) {
        creationTime = java.time.ZonedDateTime.now();
        this.fileName = fileName;
        JsonManager jsonManager = new JsonManager(stream, this);
        HashSet<Dragon> collection = jsonManager.readJson();
        if (collection != null) {
            this.collection = collection;
        }
        Dragon.setNextId(countNextId());
    }

    /**
     * Считает следующее id для Dragon
     * @return следующее id
     */
    private long countNextId() {
        long id = 0;
        for (Dragon dragon : collection) {
            if (dragon.getId() > id) {
                id = dragon.getId();
            }
        }
        return id + 1;
    }

    public ZonedDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Возвращает класс коллекции
     *
     * @return класс коллекции
     */
    public Class<?> getCollectionType() {
        return collection.getClass();
    }

    /**
     * Возвращает размер коллекции
     *
     * @return размер коллекции
     */
    public int getCollectionSize() {
        return collection.size();
    }

    /**
     * Проверяет, пуста ли коллекция
     *
     * @return результат проверки
     */
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param dragon добавляемый элемент
     */
    public void add(Dragon dragon) {
        collection.add(dragon);
        Dragon.increaseNextId();
    }

    /**
     * Удаляет элемент из коллекции
     *
     * @param dragon удаляемый элемент
     */
    public void remove(Dragon dragon) {
        collection.remove(dragon);
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        collection.clear();
        Dragon.setNextId(1);
    }

    /**
     * Возвращает элемент с заданным айди
     *
     * @param id заданный айди
     * @return элемент
     */
    public Dragon findById(long id) {
        for (Dragon dragon : collection) {
            if (dragon.getId() == id) {
                return dragon;
            }
        }
        return null;
    }

    /**
     * Возвращает минимальный элемент коллекции
     *
     * @return минимальный элемент коллекции
     */
    public Dragon findMin() {
        if (collection.isEmpty()) {
            return null;
        }
        Iterator<Dragon> iterator = collection.iterator(); // из-за HashSet приходится использовать итераторы (другой индексации нет)
        Dragon min = iterator.next();
        while (iterator.hasNext()) {
            Dragon dragon = iterator.next();
            if (min.compareTo(dragon) > 0) {
                min = dragon;
            }
        }
        return min;
    }

    /**
     * Возвращает максимальный элемент коллекции
     *
     * @return максимальный элемент коллекции
     */
    public Dragon findMax() {
        if (collection.isEmpty()) {
            return null;
        }
        Iterator<Dragon> iterator = collection.iterator();
        Dragon max = iterator.next();
        while (iterator.hasNext()) {
            Dragon dragon = iterator.next();
            if (max.compareTo(dragon) < 0) {
                max = dragon;
            }
        }
        return max;
    }

    /**
     * Удаляет элементы меньше введенного
     *
     * @param dragon введенный элемент
     * @return количество удаленных элементов
     */
    public int removeLower(Dragon dragon) {
        List<Dragon> list = new ArrayList<>();
        int count = 0;
        for (Dragon dragonI : collection) {
            if (dragon.compareTo(dragonI) > 0) {
                count++;
                list.add(dragonI); // Если сразу же удалять, будет ошибка с итерированием HashSet'а. Поэтому такой костыль
            }
        }
        for (Dragon dragonI : list) {
            collection.remove(dragonI);
        }
        return count;
    }

    public HashSet<Dragon> getCollection() {
        return collection;
    }
}
