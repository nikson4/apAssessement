package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PlayersRepository extends JpaRepository<Player,Long> {

    //Raw SQL query - select p.player_id,p.age,p.email,p.gender,p.level from players p where p.age=? and p.level=? and p.gender=?
    List<Player> findByAgeAndLevelAndGender(Integer age, Integer level, String gender);

    @Query("SELECT p FROM Player p WHERE p.sports IS EMPTY")
    List<Player> findPlayersWithNoSports();

    Player findByEmail(String email);

    @Query("SELECT DISTINCT p FROM Player p JOIN p.sports s WHERE s.name IN :sportNames")
    Page<Player> findPlayersBySports(@Param("sportNames") Set<String> sportNames, Pageable pageable);
}
