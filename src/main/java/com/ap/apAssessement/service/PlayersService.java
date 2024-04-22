package com.ap.apAssessement.service;

import com.ap.apAssessement.dto.PlayerDTO;
import com.ap.apAssessement.dto.SportDTO;
import com.ap.apAssessement.model.Player;
import com.ap.apAssessement.model.Sport;
import com.ap.apAssessement.repository.PlayersRepository;
import com.ap.apAssessement.repository.SportsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayersService {

    @Autowired
    PlayersRepository playersRepository;

    @Autowired
    SportsRepository sportsRepository;

    public List<PlayerDTO> findPlayersWithNoSports() {
        List<Player> players = playersRepository.findPlayersWithNoSports();
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for (Player player : players) {
            PlayerDTO playerDTO = new PlayerDTO(player.getEmail(), player.getLevel(), player.getAge(), player.getGender(), null);
            playersDTO.add(playerDTO);
        }
        return playersDTO;
    }

    @Transactional
    public void updatePlayerSports(String email, Set<String> sportNames) {
        Player player = playersRepository.findByEmail(email);
        if(player == null) {
            throw new EntityNotFoundException("Player not found with email: " + email);
        }

        List<Sport> sports = sportsRepository.findByNameIn(sportNames);
        if(sports.size() != sportNames.size()) {
            for(Sport sport : sports) {
                sportNames.remove(sport.getName());
            }
            throw new EntityNotFoundException("Input sports - " + sportNames + " not found in DB");
        }

        player.setSports(new HashSet<>(sports));
        playersRepository.save(player);
    }

    public Page<PlayerDTO> fetchPlayersBySports(Set<String> sportNames, Pageable pageable) {
        Page<Player> players = playersRepository.findPlayersBySports(sportNames, pageable);
        List<Player> playerList = players.getContent();
        List<PlayerDTO> playersDTO = new ArrayList<>();

        for(Player player: playerList) {
            Set<SportDTO> sportDTOSet = new HashSet<>();
            if(player.getSports() != null) {
                for(Sport sport: player.getSports()) {
                    SportDTO sportDTO = new SportDTO(sport.getName(), null);
                    sportDTOSet.add(sportDTO);
                }
            }

            PlayerDTO playerDTO = new PlayerDTO(player.getEmail(), player.getLevel(), player.getAge(), player.getGender(), sportDTOSet);
            playersDTO.add(playerDTO);
        }

        return new PageImpl<>(playersDTO, players.getPageable(), players.getTotalElements());
    }
}
