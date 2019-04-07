package com.yay.linda.pixelstarshipscrew.service;

import com.yay.linda.pixelstarshipscrew.entity.UserCrewEntity;
import com.yay.linda.pixelstarshipscrew.model.Crew;
import com.yay.linda.pixelstarshipscrew.model.User;
import com.yay.linda.pixelstarshipscrew.model.UserCrew;
import com.yay.linda.pixelstarshipscrew.repository.UserCrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCrewRepository userCrewRepository;

    @Autowired
    private List<Crew> crew;

    public List<UserCrew> getCrewForUser(String sessionToken) {
        User user = userService.getUserFromSessionToken(sessionToken);

        return userCrewRepository.findByUsername(user.getUsername()).stream()
                .map(UserCrew::new)
                .collect(Collectors.toList());
    }

    public List<UserCrew> addCrewForUser(String sessionToken, List<String> crewToAdd) {
        User user = userService.getUserFromSessionToken(sessionToken);

        crewToAdd.forEach(
                c -> crew.stream()
                        .filter(cc -> cc.getName().equalsIgnoreCase(c) || cc.getId().equals(c))
                        .findFirst()
                        .ifPresent(cc -> userCrewRepository.save(new UserCrewEntity(user.getUsername(), cc.getName()))));

        return getCrewForUser(sessionToken);
    }
}
