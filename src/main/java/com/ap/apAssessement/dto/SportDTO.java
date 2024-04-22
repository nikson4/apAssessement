package com.ap.apAssessement.dto;

import java.util.Set;

public class SportDTO {

    private String name;

    private Set<PlayerDTO> players;

    public SportDTO(String name, Set<PlayerDTO> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerDTO> players) {
        this.players = players;
    }
}
