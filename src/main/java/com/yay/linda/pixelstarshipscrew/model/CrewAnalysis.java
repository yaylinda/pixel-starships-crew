package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class CrewAnalysis {

    private Integer crewCount;

    private Map<String, List<String>> specialAbilities;
    private Map<String, List<String>> collections;
    private Map<String, List<String>> rarities;
    private Map<String, List<String>> weaponSlots;

    private TreeMap<Double, List<String>> bestPilot;
    private TreeMap<Double, List<String>> bestScience;
    private TreeMap<Double, List<String>> bestEngineer;
    private TreeMap<Double, List<String>> bestWeapon;

    public CrewAnalysis(Integer crewCount) {
        this.crewCount = crewCount;

        this.specialAbilities = new HashMap<>();
        this.collections = new HashMap<>();
        this.rarities = new HashMap<>();
        this.weaponSlots = new HashMap<>();

        this.bestPilot = new TreeMap<>(Collections.reverseOrder());
        this.bestScience = new TreeMap<>(Collections.reverseOrder());
        this.bestEngineer = new TreeMap<>(Collections.reverseOrder());
        this.bestWeapon = new TreeMap<>(Collections.reverseOrder());
    }

    public void collectStat(AnalysisType type, String statName, String crewName, Double statValue) {
        switch (type) {
            case SPECIAL_ABILITY:
                addToMap(this.specialAbilities, statName, crewName);
                break;
            case COLLECTION:
                addToMap(this.collections, statName, crewName);
                break;
            case RARITY:
                addToMap(this.rarities, statName, crewName);
                break;
            case WEAPON_SLOT:
                addToMap(this.weaponSlots, statName, crewName);
                break;
            case PILOT:
                addToMap(this.bestPilot, statValue, crewName);
                break;
            case SCIENCE:
                addToMap(this.bestScience, statValue, crewName);
                break;
            case ENGINEER:
                addToMap(this.bestEngineer, statValue, crewName);
                break;
            case WEAPON:
                addToMap(this.bestWeapon, statValue, crewName);
                break;
        }
    }

    private void addToMap(Map<String, List<String>> map, String key, String value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    private void addToMap(TreeMap<Double, List<String>> map, Double key, String value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }
}
