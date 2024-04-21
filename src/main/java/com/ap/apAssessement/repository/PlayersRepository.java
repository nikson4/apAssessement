package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersRepository extends JpaRepository<Player,Long> {
    /*
    select *
    from Players where age = ? and level = ? and gender = ?;
     */
    List<Player> findByAgeAndLevelAndGender(Integer age, Integer level, String gender);
}
