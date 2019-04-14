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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.COLLECTION;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.ENGINEER;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.PILOT;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.RARITY;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.SCIENCE;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.SPECIAL_ABILITY;
import static com.yay.linda.pixelstarshipscrew.model.AnalysisType.WEAPON;
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
            String crewName = c.getCrewName();
            analysis.collectStat(SPECIAL_ABILITY, c.getSpecialAbility(), crewName, null);
            analysis.collectStat(COLLECTION, c.getCollection(), crewName, null);
            analysis.collectStat(RARITY, c.getRarity(), crewName, null);
            c.getWeaponSlots().forEach(w -> analysis.collectStat(WEAPON_SLOT, w, crewName, null));
            analysis.collectStat(PILOT, null, crewName, c.getPilot());
            analysis.collectStat(SCIENCE, null, crewName, c.getScience());
            analysis.collectStat(ENGINEER, null, crewName, c.getEngineer());
            analysis.collectStat(WEAPON, null, crewName, c.getWeapon());
        });

        return analysis;
    }
}
