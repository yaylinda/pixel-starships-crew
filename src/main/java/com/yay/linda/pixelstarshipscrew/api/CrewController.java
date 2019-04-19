package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.CrewAnalysis;
import com.yay.linda.pixelstarshipscrew.model.RankScore;
import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/crew")
@CrossOrigin
public class CrewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private CrewService crewService;

    @GetMapping("")
    public ResponseEntity<List<UserCrew>> getCrewForUser(
            @RequestHeader("Session-Token") String sessionToken) {
        LOGGER.info("GET - crew for user");
        return ResponseEntity.ok(crewService.getCrewForUser(sessionToken));
    }

    @PostMapping("")
    public ResponseEntity<List<UserCrew>> addCrewForUser(
            @RequestHeader("Session-Token") String sessionToken,
            @RequestBody List<String> crewToAdd) {
        LOGGER.info("POST - crew for user");
        return ResponseEntity.ok(crewService.addCrewForUser(sessionToken, crewToAdd));
    }

    @DeleteMapping("")
    public ResponseEntity<List<UserCrew>> deleteCrewForUser(
            @RequestHeader("Session-Token") String sessionToken,
            @RequestBody List<String> crewToDelete) {
        LOGGER.info("DELETE - crew for user");
        return ResponseEntity.ok(crewService.deleteCrewForUser(sessionToken, crewToDelete));
    }

    @GetMapping("/analysis")
    public ResponseEntity<CrewAnalysis> getCrewAnalysisForUser(
            @RequestHeader("Session-Token") String sessionToken) {
        LOGGER.info("GET - crew analysis for user");
        return ResponseEntity.ok(crewService.getCrewAnalysisForUser(sessionToken));
    }
}
