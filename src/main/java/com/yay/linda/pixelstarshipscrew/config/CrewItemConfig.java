package com.yay.linda.pixelstarshipscrew.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yay.linda.pixelstarshipscrew.model.CrewData;
import com.yay.linda.pixelstarshipscrew.model.ItemData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class CrewItemConfig {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public List<CrewData> crew() throws IOException {
        File file = ResourceUtils.getFile("classpath:crew.json");
        Map<String, CrewData> crewData = objectMapper.readValue(file, new TypeReference<Map<String, CrewData>>() {});
        return new ArrayList<>(crewData.values());
    }

    @Bean
    public List<ItemData> items() throws IOException {
        File file = ResourceUtils.getFile("classpath:items.json");
        Map<String, ItemData> itemData = objectMapper.readValue(file, new TypeReference<Map<String, ItemData>>() {});
        return new ArrayList<>(itemData.values());
    }
}
