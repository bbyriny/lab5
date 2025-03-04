package org.example.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.example.object.Dragon;
import org.example.utils.ZonedDateTimeSerializer;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.HashSet;

/**
 * Класс для чтения и записи данных json
 */
public class JsonManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting() // для красивого вывода json
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer()) // для обработки ZonedDateTime (по умолчанию не обрабатывает)
            .serializeNulls() // для отображения null
            .create();

    private final String fileName;

    private final StreamManager stream;
    private final CollectionManager collectionManager;

    public JsonManager(StreamManager stream, CollectionManager collectionManager) {
        this.stream = stream;
        this.collectionManager = collectionManager;
        this.fileName = collectionManager.fileName;
    }

    public void writeJson() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(gson.toJson(collectionManager.getCollection()));
        } catch (IOException e) {
            stream.print("Не удалось открыть файл для загрузки\n");
        }
    }

    public HashSet<Dragon> readJson() {
        if (fileName == null || fileName.isEmpty()) {
            stream.print("Имя для загрузочного файла не указано\n");
            return null;
        }
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName))) {
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.append(line.trim());
                }
            }
            if (lines.isEmpty()) {
                stream.print("Файл для загрузки пуст\n");
                return null;
            }
            HashSet<Dragon> dragons = gson.fromJson(lines.toString(), new TypeToken<HashSet<Dragon>>() {
            });
            stream.print("Коллекция успешно загружена\n");
            return dragons;
        } catch (FileNotFoundException e) {
            stream.print("Файл для загрузки не найден\n");
        } catch (JsonParseException e) {
            stream.print("Ошибка в синтаксисе json файла\n");
        } catch (IOException e) {
            stream.print("Непредусмотренная ошибка :c\n");
            System.exit(0);
        }
        return null;
    }
}
