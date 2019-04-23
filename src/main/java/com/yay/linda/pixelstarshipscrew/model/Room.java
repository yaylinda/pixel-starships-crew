package com.yay.linda.pixelstarshipscrew.model;

import lombok.Getter;

import static com.yay.linda.pixelstarshipscrew.model.RoomNeed.ENGINE;
import static com.yay.linda.pixelstarshipscrew.model.RoomNeed.PILOT;
import static com.yay.linda.pixelstarshipscrew.model.RoomNeed.SCIENCE;
import static com.yay.linda.pixelstarshipscrew.model.RoomNeed.WEAPON;

@Getter
public enum Room {
    PHOTON_PHASER(SCIENCE, 2),
    ANTI_CRAFT_LASER(WEAPON, 2),
    TELEPORTER(SCIENCE, 3),
    LASER_BLASTER(WEAPON, 3),
    MINERAL_MINING_LASER(WEAPON, 3),
    MISSILE_LAUNCHER(WEAPON, 3),
    SHIELD_GENERATOR(SCIENCE, 3),
    PLASMA_DISCHARGER(WEAPON, 3),
    EMP_CANNON(WEAPON, 4),
    HANGAR(PILOT, 5),
    ANDROID_STUDIO(ENGINE, 2);

    private RoomNeed need;
    private Integer capacity;

    Room(RoomNeed need, Integer capacity) {
        this.need = need;
        this.capacity = capacity;
    }
}
