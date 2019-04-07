package com.yay.linda.pixelstarshipscrew.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yay.linda.pixelstarshipscrew.model.JsonCrew;
import com.yay.linda.pixelstarshipscrew.model.JsonItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class CrewItemConfig {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public List<JsonCrew> crew() throws IOException {
        File file = ResourceUtils.getFile("classpath:crew.json");
        Map<String, JsonCrew> crewData = objectMapper.readValue(file, new TypeReference<Map<String, JsonCrew>>() {});
        return new ArrayList<>(crewData.values());
    }

    @Bean
    public List<JsonItem> items() {

        return new ArrayList<>();
    }
}
