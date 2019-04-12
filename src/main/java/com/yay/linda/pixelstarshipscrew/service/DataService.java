package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.model.CrewData;
import com.yay.linda.pixelstarshipscrew.model.ItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataService {

    private List<CrewData> crewData;

    private List<ItemData> itemData;

    private Map<String, CrewData> nameToCrewMap;
    private Map<Integer, CrewData> idToCrewMap;

    private Map<String, ItemData> nameToItemMap;
    private Map<Integer, ItemData> idToItemMap;

    @Autowired
    public DataService(List<CrewData> crewData, List<ItemData> itemData) {
        nameToCrewMap = new HashMap<>();
        idToCrewMap = new HashMap<>();

        nameToItemMap = new HashMap<>();
        idToItemMap = new HashMap<>();

        nameToCrewMap = crewData.stream().collect(Collectors.toMap(c -> c.getName().toLowerCase(), c -> c));
        idToCrewMap = crewData.stream().collect(Collectors.toMap(CrewData::getId, c -> c));

        nameToItemMap = itemData.stream().collect(Collectors.toMap(c -> c.getName().toLowerCase(), i -> i));
        idToItemMap = itemData.stream().collect(Collectors.toMap(ItemData::getId, i -> i));
    }

    public List<CrewData> getCrewData() throws IOException {
        return crewData;
    }

    public List<ItemData> getItemData() throws IOException {
        return itemData;
    }

    public Optional<CrewData> getCrewDataByName(String name) {
        if (nameToCrewMap.containsKey(name.toLowerCase())) {
            return Optional.of(nameToCrewMap.get(name.toLowerCase()));
        } else {
            return Optional.empty();
        }
    }

    public CrewData getCrewDataById(Integer id) {
        return idToCrewMap.get(id);
    }

    public ItemData getItemDataByName(String name) {
        return nameToItemMap.get(name.toLowerCase());
    }

    public ItemData getItemDataById(Integer id) {
        return idToItemMap.get(id);
    }
}
