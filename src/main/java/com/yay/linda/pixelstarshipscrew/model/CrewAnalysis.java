package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Data
public class CrewAnalysis {

    private Integer crewCount;

    private Map<String, List<String>> specialAbilities;
    private Map<String, List<String>> collections;
    private Map<String, List<String>> rarities;
    private Map<String, List<String>> weaponSlots;

    private TreeMap<Double, List<String>> bestHp;
    private TreeMap<Double, List<String>> bestAttack;
    private TreeMap<Double, List<String>> bestPilot;
    private TreeMap<Double, List<String>> bestScience;
    private TreeMap<Double, List<String>> bestEngineer;
    private TreeMap<Double, List<String>> bestWeapon;

    private Map<String, Map<String, RankScore>> stats;

    public CrewAnalysis(Integer crewCount) {
        this.crewCount = crewCount;

        this.specialAbilities = new HashMap<>();
        this.collections = new HashMap<>();
        this.rarities = new HashMap<>();
        this.weaponSlots = new HashMap<>();

        this.bestHp = new TreeMap<>(Collections.reverseOrder());
        this.bestAttack = new TreeMap<>(Collections.reverseOrder());
        this.bestPilot = new TreeMap<>(Collections.reverseOrder());
        this.bestScience = new TreeMap<>(Collections.reverseOrder());
        this.bestEngineer = new TreeMap<>(Collections.reverseOrder());
        this.bestWeapon = new TreeMap<>(Collections.reverseOrder());
    }

    public void collectStat(StatType type, String statName, String crewName, Double statValue) {
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
            case HP:
                addToMap(this.bestHp, statValue, crewName);
                break;
            case ATTACK:
                addToMap(this.bestAttack, statValue, crewName);
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

    public RankScore getRankScore(StatType statType, String crewName, Double crewValue) {
        TreeMap<Double, List<String>> statsMap;
        switch (statType) {
            case HP:
                statsMap = this.bestHp;
                break;
            case ATTACK:
                statsMap = this.bestAttack;
                break;
            case PILOT:
                statsMap = this.bestPilot;
                break;
            case SCIENCE:
                statsMap = this.bestScience;
                break;
            case ENGINEER:
                statsMap = this.bestEngineer;
                break;
            case WEAPON:
                statsMap = this.bestWeapon;
                break;
            default:
                return null;
        }

        return RankScore.builder()
                .rank(getIndexFromMap(statsMap, crewName))
                .outOf(statsMap.size())
                .value(crewValue)
                .min(statsMap.lastKey())
                .max(statsMap.firstKey())
                .average(calculateAverageFromTreeMap(statsMap))
                .score((crewValue - statsMap.lastKey()) / (statsMap.firstKey() - statsMap.lastKey()))
                .indicator("") // TODO - figure out what this is...
                .build();


    }
    public Integer getRankForCrewStat(StatType statType, String crewName) {
        switch (statType) {
            case HP:
                return getIndexFromMap(this.bestHp, crewName);
            case ATTACK:
                return getIndexFromMap(this.bestAttack, crewName);
            case PILOT:
                return getIndexFromMap(this.bestPilot, crewName);
            case SCIENCE:
                return getIndexFromMap(this.bestScience, crewName);
            case ENGINEER:
                return getIndexFromMap(this.bestEngineer, crewName);
            case WEAPON:
                return getIndexFromMap(this.bestWeapon, crewName);
            default:
                return null;
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

    private Integer getIndexFromMap(TreeMap<Double, List<String>> map, String crewName) {
        int i = 1;
        for (Double key : map.keySet()) {
            if (map.get(key).contains(crewName)) {
                break;
            }
            i++;
        }
        return i;
    }

    private Double calculateAverageFromTreeMap(TreeMap<Double, List<String>> map) {
        double summedValue = map.keySet().stream()
                .map(x -> x * map.get(x).size())
                .mapToDouble(Double::doubleValue)
                .sum();
        return summedValue / this.crewCount;
    }
}
