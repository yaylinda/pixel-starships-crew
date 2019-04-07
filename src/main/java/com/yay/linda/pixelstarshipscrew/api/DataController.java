package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.Crew;
import com.yay.linda.pixelstarshipscrew.model.Item;
import com.yay.linda.pixelstarshipscrew.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/data")
@CrossOrigin
public class DataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @GetMapping("/crew")
    public ResponseEntity<List<Crew>> getCrew() throws IOException {
        LOGGER.info("GET - crew data");
        return ResponseEntity.ok(dataService.getCrew());
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems() throws IOException {
        LOGGER.info("GET - items data");
        return ResponseEntity.ok(dataService.getItems());
    }
}
