package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.UserCrewEntity;
import com.yay.linda.pixelstarshipscrew.model.CrewData;
import com.yay.linda.pixelstarshipscrew.model.CrewAnalysis;
import com.yay.linda.pixelstarshipscrew.model.RankScore;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.repository.UserCrewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.yay.linda.pixelstarshipscrew.model.StatType.ATTACK;
import static com.yay.linda.pixelstarshipscrew.model.StatType.COLLECTION;
import static com.yay.linda.pixelstarshipscrew.model.StatType.ENGINEER;
import static com.yay.linda.pixelstarshipscrew.model.StatType.HP;
import static com.yay.linda.pixelstarshipscrew.model.StatType.PILOT;
import static com.yay.linda.pixelstarshipscrew.model.StatType.RARITY;
import static com.yay.linda.pixelstarshipscrew.model.StatType.SCIENCE;
import static com.yay.linda.pixelstarshipscrew.model.StatType.SPECIAL_ABILITY;
import static com.yay.linda.pixelstarshipscrew.model.StatType.WEAPON;
import static com.yay.linda.pixelstarshipscrew.model.StatType.WEAPON_SLOT;

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

    public List<UserCrew> deleteCrewForUser(String sessionToken, List<String> crewToDelete) {
        User user = userService.getUserFromSessionToken(sessionToken);

        List<UserCrewEntity> currentCrew = userCrewRepository.findByUsername(user.getUsername());

        LOGGER.info("Retrieved {} current crew for {}", currentCrew.size(), user.getUsername());

        for (UserCrewEntity crew : currentCrew) {
            String crewName = dataService.getCrewDataById(crew.getCrewId()).getName();
            if (crewToDelete.contains(crewName.toLowerCase())) {
                LOGGER.info("Deleting crew: {}", crewName);
                userCrewRepository.deleteById(crew.getId());
            }
        }

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
            analysis.collectStat(HP, null, crewName, c.getHp());
            analysis.collectStat(ATTACK, null, crewName, c.getAttack());
            analysis.collectStat(PILOT, null, crewName, c.getPilot());
            analysis.collectStat(SCIENCE, null, crewName, c.getScience());
            analysis.collectStat(ENGINEER, null, crewName, c.getEngineer());
            analysis.collectStat(WEAPON, null, crewName, c.getWeapon());
        });

        Map<String, Map<String, RankScore>> statsMap = new HashMap<>();
        crew.forEach(c -> {
            String crewName = c.getCrewName();

            Map<String, RankScore> ranks = new HashMap<>();

            ranks.put("hp", analysis.getRankScore(HP, crewName, c.getHp()));
            ranks.put("attack", analysis.getRankScore(ATTACK, crewName, c.getAttack()));
            ranks.put("pilot", analysis.getRankScore(PILOT, crewName, c.getPilot()));
            ranks.put("science", analysis.getRankScore(SCIENCE, crewName, c.getScience()));
            ranks.put("engineer", analysis.getRankScore(ENGINEER, crewName, c.getEngineer()));
            ranks.put("weapon", analysis.getRankScore(WEAPON, crewName, c.getWeapon()));

            statsMap.put(crewName, ranks);
        });
        analysis.setStats(statsMap);

        return analysis;
    }
//
//    public Map<String, Map<String, RankScore>> getCrewStatsRankScore(String sessionToken) {
//        List<UserCrew> crew = getCrewForUser(sessionToken);
//        CrewAnalysis analysis = getCrewAnalysisForUser(sessionToken);
//
//        Map<String, Map<String, RankScore>> map = new HashMap<>();
//
//        crew.forEach(c -> {
//            String crewName = c.getCrewName();
//            Map<String, RankScore> ranks = new HashMap<>();
//
//            ranks.put("hp", RankScore.builder()
//                    .rank(analysis.getRankForCrewStat(HP, crewName))
//                    .outOf(analysis.getBestHp().size())
//                    .score((c.getHp() - analysis.getBestHp().lastKey()) / (analysis.getBestHp().firstKey() - analysis.getBestHp().lastKey()))
//                    .value(c.getHp())
//                    .build());
//
//            ranks.put("pilot", RankScore.builder()
//                    .rank(analysis.getRankForCrewStat(PILOT, crewName))
//                    .outOf(analysis.getBestPilot().size())
//                    .score(c.getPilot() / analysis.getBestPilot().firstKey())
//                    .value(c.getPilot())
//                    .build());
//
//            ranks.put("science", RankScore.builder()
//                    .rank(analysis.getRankForCrewStat(SCIENCE, crewName))
//                    .outOf(analysis.getBestScience().size())
//                    .score(c.getScience() / analysis.getBestScience().firstKey())
//                    .value(c.getScience())
//                    .build());
//
//            ranks.put("engineer", RankScore.builder()
//                    .rank(analysis.getRankForCrewStat(ENGINEER, crewName))
//                    .outOf(analysis.getBestEngineer().size())
//                    .score(c.getEngineer() / analysis.getBestEngineer().firstKey())
//                    .value(c.getEngineer())
//                    .build());
//
//            ranks.put("weapon", RankScore.builder()
//                    .rank(analysis.getRankForCrewStat(WEAPON, crewName))
//                    .outOf(analysis.getBestWeapon().size())
//                    .score(c.getWeapon() / analysis.getBestWeapon().firstKey())
//                    .value(c.getWeapon())
//                    .build());
//
//            map.put(crewName, ranks);
//        });
//
//        return map;
//    }
//
//    public Map<String, Map<String, String>> getCrewItemRecommendationsForUser(String sessionToken) {
//        List<UserCrew> crew = getCrewForUser(sessionToken);
//        CrewAnalysis analysis = getCrewAnalysisForUser(sessionToken);
//
//        Map<String, Map<String, String>> crewItemMap = new HashMap<>();
//
//        crew.forEach(c -> {
//
//            if (c.getSpecialAbility().equalsIgnoreCase("poison gas")) {
//                Map<String, String> slotsToItemsMap = new HashMap<>();
//                c.getWeaponSlots().forEach(w -> {
//                    slotsToItemsMap.put(w, "+ATK, +HP");
//                });
//                crewItemMap.put(c.getCrewName(), slotsToItemsMap);
//            } else {
//
//                List<String> bestPilots = analysis.getBestPilot().get(0);
////                if (c.getCrewName().equalsIgnoreCase())
//            }
//
//
//
//        });
//
//        return crewItemMap;
//    }
}
