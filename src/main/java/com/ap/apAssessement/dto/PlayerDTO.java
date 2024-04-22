package com.ap.apAssessement.dto;


import java.util.Set;

public class PlayerDTO {

    private String email;

    private Integer level;

    private Integer age;

    private String gender;

    private Set<SportDTO> sports;

    public PlayerDTO(String email, Integer level, Integer age, String gender, Set<SportDTO> sports) {
        this.email = email;
        this.level = level;
        this.age = age;
        this.gender = gender;
        this.sports = sports;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<SportDTO> getSports() {
        return sports;
    }

    public void setSports(Set<SportDTO> sports) {
        this.sports = sports;
    }
}
