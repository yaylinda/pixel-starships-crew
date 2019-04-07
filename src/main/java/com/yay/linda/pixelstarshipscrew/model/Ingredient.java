package com.yay.linda.pixelstarshipscrew.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    private Integer count;
    private String name;
}
