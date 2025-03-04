package org.example.object;

import org.example.utils.Validatable;
import org.example.utils.ValidationError;

public class DragonCave implements Validatable {
    private final Double depth; //Поле не может быть null
    private final Float numberOfTreasures; //Поле не может быть null, Значение поля должно быть больше 0

    public DragonCave(Double depth, Float numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
        if (!isValid()) {
            throw new ValidationError("DragonCave");
        }
    }

    @Override
    public boolean isValid() {
        if (depth == null) return false;
        if (numberOfTreasures == null) return false;
        return numberOfTreasures > 0;
    }

    public String toString() {
        return "DragonCave {" +
                "depth=" + depth +
                ", numberOfTreasures=" + numberOfTreasures +
                "}";
    }
}
