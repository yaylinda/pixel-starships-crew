package com.yay.linda.pixelstarshipscrew.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private Integer count;
    private Integer p25;
    private Integer p50;
    private Integer p75;
}
