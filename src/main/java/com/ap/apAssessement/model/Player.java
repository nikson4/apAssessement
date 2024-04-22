package com.ap.apAssessement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "players", indexes = @Index(columnList = "email"))
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Integer age;

    private String gender;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Players_Sports",
            joinColumns = { @JoinColumn(name = "player_id") },
            inverseJoinColumns = { @JoinColumn(name = "sport_id") }
    )
    private Set<Sport> sports = new HashSet<>();

    public Player() {

    }

    public Player(String email, Integer level, Integer age, String gender) {
        this.email = email;
        this.level = level;
        this.age = age;
        this.gender = gender;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getEmail() {
        return email;
    }
    public Integer getLevel() {
        return level;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Set<Sport> getSports() { return sports; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLevel(Integer level) {
        if (level == null || level < 1 || level > 10) {
            throw new IllegalArgumentException("Level must be between 1 and 10");
        }
        this.level = level;
    }

    public void setGender(String gender) {
        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
            throw new IllegalArgumentException("Gender must be either 'Male' or 'Female'");
        }
        this.gender = gender;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
