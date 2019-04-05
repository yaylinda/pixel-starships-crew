package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.Crew;
import com.yay.linda.pixelstarshipscrew.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crew")
@CrossOrigin
public class CrewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private CrewService crewService;

    @GetMapping("/")
    public ResponseEntity<List<Crew>> getCrew(@RequestHeader("Session-Token") String sessionToken) {
        LOGGER.info("GET - crew: sessionToken={}", sessionToken);
        return ResponseEntity.ok(crewService.getCrew());
    }
}
