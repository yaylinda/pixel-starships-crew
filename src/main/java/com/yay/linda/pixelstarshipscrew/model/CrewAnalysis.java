package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CrewAnalysis {
    private Integer crewCount;
    private Map<String, List<String>> specialAbilities;
    private Map<String, List<String>> collections;
    private Map<String, List<String>> rarities;
    private Map<String, List<String>> weaponSlots;

    public CrewAnalysis(Integer crewCount) {
        this.crewCount = crewCount;
        this.specialAbilities = new HashMap<>();
        this.collections = new HashMap<>();
        this.rarities = new HashMap<>();
        this.weaponSlots = new HashMap<>();
    }

    public void addStat(AnalysisType type, String statName, String crewName) {
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
        }
    }

    private void addToMap(Map<String, List<String>> map, String key, String value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }
}
