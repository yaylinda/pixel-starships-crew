package com.yay.linda.pixelstarshipscrew.model;

public class Stat {

    public enum Type {
        HP,
        ATTACK,
        REPAIR,
        ABILITY,
        STAMINA,
        PILOT,
        SCIENCE,
        ENGINEER,
        WEAPON,
        TRAINING;
    }

    private Type type;
    private Double value;
    private Double maxValue;
}
