package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.List;

@Data
public class UserCrew {
    private String crewName;
    private Integer crewId;
    private String specialAbility;
    private String collection;
    private Double hp;
    private Double attack;
    private Double repair;
    private Double ability;
    private Double stamina;
    private Double pilot;
    private Double science;
    private Double engineer;
    private Double weapon;
    private List<WeaponSlot> weaponSlots;

    public UserCrew(CrewData crewData) {
        this.crewName = crewData.getName();
    }
}
