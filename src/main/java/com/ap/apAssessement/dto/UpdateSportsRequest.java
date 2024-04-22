package com.ap.apAssessement.dto;

import java.util.Set;

public class UpdateSportsRequest {
    private String email;
    private Set<String> sports;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getSports() {
        return sports;
    }

    public void setSports(Set<String> sports) {
        this.sports = sports;
    }
}
