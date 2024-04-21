package com.ap.apAssessement.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sports", indexes = @Index(columnList = "name"))
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sportId;

    @Column(nullable = false)
    private String name;

//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "Players_Sports",
//            joinColumns = { @JoinColumn(name = "sport_id") },
//            inverseJoinColumns = { @JoinColumn(name = "player_id") }
//    )
    @ManyToMany(mappedBy = "sports")
    private Set<Player> players = new HashSet<>();

    public Sport() {
    }

    public Sport(String name) {
        this.name = name;
    }

    public Long getSportId() {
        return sportId;
    }

    public String getName() { return name; }

    public Set<Player> getPlayers() { return players; };

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
