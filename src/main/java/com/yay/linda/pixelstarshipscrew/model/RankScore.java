package com.yay.linda.pixelstarshipscrew.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankScore {
    private Integer rank;
    private Integer outOf;
    private Double score;
    private Double value;
    private Double min;
    private Double max;
    private Double average;
    private String indicator;
}
