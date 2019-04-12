package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.UserCrewEntity;
import com.yay.linda.pixelstarshipscrew.model.CrewData;
import com.yay.linda.pixelstarshipscrew.model.CrewAnalysis;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.repository.UserCrewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.COLLECTION;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.RARITY;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.SPECIAL_ABILITY;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.WEAPON_SLOT;

@Service
public class CrewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserCrewRepository userCrewRepository;

    @Autowired
    private List<CrewData> crewData;

    @Autowired
    private DataService dataService;

    public List<UserCrew> getCrewForUser(String sessionToken) {
        User user = userService.getUserFromSessionToken(sessionToken);

        return userCrewRepository.findByUsername(user.getUsername()).stream()
                .map(e -> new UserCrew(dataService.getCrewDataById(e.getCrewId())))
                .collect(Collectors.toList());
    }

    public List<UserCrew> addCrewForUser(String sessionToken, List<String> crewToAdd) {
        User user = userService.getUserFromSessionToken(sessionToken);

        crewToAdd.stream()
                .map(s -> dataService.getCrewDataByName(s))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(c -> new UserCrewEntity(user.getUsername(), c.getId()))
                .forEach(c -> userCrewRepository.save(c));

        return getCrewForUser(sessionToken);
    }

    public CrewAnalysis getCrewAnalysisForUser(String sessionToken) {
        List<UserCrew> crew = getCrewForUser(sessionToken);

        CrewAnalysis analysis = new CrewAnalysis(crew.size());

        crew.forEach(c -> {
            analysis.addStat(SPECIAL_ABILITY, c.getSpecialAbility(), c.getCrewName());
            analysis.addStat(COLLECTION, c.getCollection(), c.getCrewName());
            analysis.addStat(RARITY, c.getRarity(), c.getCrewName());
            c.getWeaponSlots().forEach(w -> analysis.addStat(WEAPON_SLOT, w, c.getCrewName()));
        });

        return analysis;
    }
}
