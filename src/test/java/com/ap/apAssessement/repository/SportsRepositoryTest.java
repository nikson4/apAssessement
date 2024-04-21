package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Player;
import com.ap.apAssessement.model.Sport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SportsRepositoryTest {

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private SportsRepository sportsRepository;

    @Test
    void testFindSportsWithMultiplePlayers() {
        Player inputPlayer1 = new Player("player2@test.com", 1, 21, "Male");
        Player inputPlayer2 = new Player("player3@test.com", 2, 22, "Female");

        Sport sport1 = new Sport("Hockey");

        inputPlayer1.getSports().add(sport1);
        inputPlayer2.getSports().add(sport1);

        sport1.getPlayers().add(inputPlayer1);
        sport1.getPlayers().add(inputPlayer2);

        playersRepository.save(inputPlayer1);
        playersRepository.save(inputPlayer2);

        sportsRepository.save(sport1);


        List<Sport> sportList = sportsRepository.findSportsWithMultiplePlayers();
        /*There are 5 sports that has multiple associated players as the data in h2 database is also holding data from data.sql
        in addition to the mock data above
         */
        assertEquals(5, sportList.size());
    }

    @Test
    void testFindSportsWithNoPlayers() {
        Sport sport2 = new Sport("Cricket");
        sportsRepository.save(sport2);
        List<Sport> sportList = sportsRepository.findSportsWithNoPlayers();
        assertEquals(1, sportList.size());
    }
}