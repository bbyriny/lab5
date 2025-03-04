package org.example.object;

import org.example.utils.Validatable;
import org.example.utils.ValidationError;

public class Dragon implements Validatable, Comparable<Dragon> {
    static long nextId = 1;

    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonCave cave; //Поле не может быть null

    public Dragon(String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, int age, Color color, DragonType type, DragonCharacter character, DragonCave cave) {
        id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        if (!isValid()) {
            throw new ValidationError("Dragon");
        }
    }

    /**
     * Обновляет объект
     *
     * @param dragon объект, поля которого возьмутся для обновления
     */
    public void update(Dragon dragon) {
        this.name = dragon.getName();
        this.coordinates = dragon.getCoordinates();
        this.creationDate = dragon.getCreationDate();
        this.age = dragon.getAge();
        this.color = dragon.getColor();
        this.type = dragon.getType();
        this.character = dragon.getCharacter();
        this.cave = dragon.getCave();
    }

    /**
     * Повышает nextId на 1
     */
    public static void increaseNextId() {
        Dragon.nextId++;
    }

    public static void setNextId(long nextId) {
        Dragon.nextId = nextId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getAge() {
        return age;
    }

    public Color getColor() {
        return color;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public DragonCave getCave() {
        return cave;
    }

    @Override
    public boolean isValid() {
        if (id <= 0) return false;
        if (name == null) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (age <= 0) return false;
        if (type == null) return false;
        if (character == null) return false;
        return cave != null;
    }

    @Override
    public String toString() {
        return "Dragon {" +
                "id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", color=" + ((color != null) ? color : "null") +
                ", type=" + type +
                ", character=" + character +
                ", cave=" + cave.toString() +
                "}";
    }

    @Override
    public int compareTo(Dragon o) {
        return Coordinates.compare(getCoordinates(), o.getCoordinates());
    }
}