package com.yay.linda.pixelstarshipscrew.api;

import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crew")
@CrossOrigin
public class CrewController {

    @Autowired
    private CrewService crewService;

    @GetMapping("")
    public ResponseEntity<List<UserCrew>> getCrewForUser(
            @RequestHeader("Session-Token") String sessionToken) {
        return ResponseEntity.ok(crewService.getCrewForUser(sessionToken));
    }

    @PostMapping("")
    public ResponseEntity<List<UserCrew>> addCrewForUser(
            @RequestHeader("Session-Token") String sessionToken,
            @RequestBody List<String> crewToAdd) {
        return ResponseEntity.ok(crewService.addCrewForUser(sessionToken, crewToAdd));
    }
}
