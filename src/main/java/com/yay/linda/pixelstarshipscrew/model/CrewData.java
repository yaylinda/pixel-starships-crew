package com.yay.linda.pixelstarshipscrew.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewData {
    private List<Double> ability;
    private List<Double> attack;
    private Integer collection;
    private String collection_name;
    private List<Double> engine;
    private Map<String, Object> equipment;
    private Integer fire_resist;
    private List<Double> hp;
    private Integer id;
    private String name;
    private List<Double> pilot;
    private String rarity;
    private Integer rarity_order;
    private List<Double> repair;
    private List<Double> research;
    private Integer resurrect;
    private Integer run;
    private List<Double> science;
    private List<Double> shield;
    private String special_ability;
    private Integer training_limit;
    private Integer walk;
    private List<Double> weapon;
}
