package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.config.CrewItemConfig;
import com.yay.linda.pixelstarshipscrew.model.Crew;
import com.yay.linda.pixelstarshipscrew.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private List<Crew> crew;

    @Autowired
    private List<Item> items;

    public List<Crew> getCrew() throws IOException {
        return crew;
    }

    public List<Item> getItems() throws IOException {
        return items;
    }
}
