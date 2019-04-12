package com.yay.linda.pixelstarshipscrew.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserCrew {
    private Integer crewId;

    private String crewName;
    private String specialAbility;
    private String collection;
    private String rarity;
    private List<String> weaponSlots;

    private Double hp;
    private Double attack;
    private Double repair;
    private Double ability;
    private Double stamina;
    private Double pilot;
    private Double science;
    private Double engineer;
    private Double weapon;

    public UserCrew(CrewData crewData) {
        this.crewId = crewData.getId();

        this.crewName = crewData.getName();
        this.specialAbility = crewData.getSpecial_ability();
        this.collection = crewData.getCollection_name();
        this.rarity = crewData.getRarity();
        this.weaponSlots = new ArrayList<>(crewData.getEquipment().keySet());

        this.hp = crewData.getHp().get(1);
        this.attack = crewData.getAttack().get(1);
        this.repair = crewData.getRepair().get(1);
        this.ability = crewData.getAbility().get(1);
        this.pilot = crewData.getPilot().get(1);
        this.science = crewData.getScience().get(1);
        this.engineer = crewData.getEngine().get(1);
        this.weapon = crewData.getWeapon().get(1);
    }
}
