package com.ap.apAssessement.service;

import com.ap.apAssessement.dto.PlayerDTO;
import com.ap.apAssessement.dto.SportDTO;
import com.ap.apAssessement.model.Player;
import com.ap.apAssessement.model.Sport;
import com.ap.apAssessement.repository.PlayersRepository;
import com.ap.apAssessement.repository.SportsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SportsService {

    @Autowired
    SportsRepository sportsRepository;

    @Autowired
    PlayersRepository playersRepository;

    public List<SportDTO> findSportsWithAssocPlayers(Set<String> sportNames) {
        List<Sport> sports = sportsRepository.findByNameIn(sportNames);
        List<SportDTO> sportsDTO = new ArrayList<>();
        for (Sport sport : sports) {
            Set<PlayerDTO> playersDTO = new HashSet<>();;
            if(sport.getPlayers() != null) {
                for (Player player : sport.getPlayers()) {
                    PlayerDTO playerDTO = new PlayerDTO(player.getEmail(), player.getLevel(), player.getAge(), player.getGender(), null);
                    playersDTO.add(playerDTO);
                }
            }
            SportDTO sportDTO = new SportDTO(sport.getName(), playersDTO);
            sportsDTO.add(sportDTO);
        }

        return sportsDTO;
    }

    @Transactional
    public Set<String> deleteSports(Set<String> sportNames) {
        List<Sport> sports = sportsRepository.findByNameIn(sportNames);
        Set<String> sportsFound = new HashSet<>();

        for(Sport sport : sports) {
            sportsFound.add(sport.getName());
            sportNames.remove(sport.getName());

            for (Player player : sport.getPlayers()) {
                player.getSports().remove(sport);
                playersRepository.save(player);
            }

            sport.getPlayers().clear();
        }
        sportsRepository.deleteAll(sports);
        return sportsFound;
    }
}
