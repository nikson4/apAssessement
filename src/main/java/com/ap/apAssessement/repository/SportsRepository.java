package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SportsRepository extends JpaRepository<Sport,Long> {

    /*
    select s.*
    from sports s
    inner join
     */
    @Query("SELECT s from Sport s JOIN s.players p GROUP BY s HAVING COUNT(p) > 1")
    List<Sport> findSportsWithMultiplePlayers();

    @Query("SELECT s FROM Sport s WHERE NOT EXISTS (SELECT p FROM Player p JOIN p.sports sp WHERE sp.id = s.id)")
    List<Sport> findSportsWithNoPlayers();
    
}
