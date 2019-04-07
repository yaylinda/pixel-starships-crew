package com.yay.linda.pixelstarshipscrew.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private Double bonus;
    private String description;
    private String disp_enhancement;
    private String enhancement;
    private Integer fair_price;
    private Integer id;
    private String ingredients;
    private Integer market_price;
    private String name;
    private Map<String, Price> prices;
    private String rarity;
    private List<Ingredient> recipe;
    private String slot;
    private String type;
}
