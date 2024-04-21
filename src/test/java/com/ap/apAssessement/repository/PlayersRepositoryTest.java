package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PlayersRepositoryTest {

    @Autowired
    private PlayersRepository playersRepository;

    @Test
    public void testFindByAgeAndLevelAndGender() {
        Player inputPlayer = new Player("player1@test.com", 3, 25, "Male");
        playersRepository.save(inputPlayer);

        List<Player> outputPlayers = playersRepository.findByAgeAndLevelAndGender(25, 3, "Male");
        assertEquals(1, outputPlayers.size());
    }
}

