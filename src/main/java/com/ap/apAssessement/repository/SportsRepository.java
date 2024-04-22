package com.ap.apAssessement.repository;

import com.ap.apAssessement.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SportsRepository extends JpaRepository<Sport,Long> {

    /*
    select s.*
    from sports s
    inner join
     */
    @Query("SELECT s from Sport s JOIN s.players p GROUP BY s HAVING COUNT(p) > 1")
    //Raw SQL query - select s.sport_id,s.name from sports s join players_sports p on s.sport_id=p.sport_id group by s.sport_id having count(p.player_id)>1
    List<Sport> findSportsWithMultiplePlayers();

    @Query("SELECT s FROM Sport s WHERE s.players IS EMPTY")
    //Raw SQL query - select s.sport_id,s.name from sports s where not exists(select 1 from players_sports p where s.sport_id=p.sport_id);
    List<Sport> findSportsWithNoPlayers();

    List<Sport> findByNameIn(Set<String> sports);

    Sport findByName(String sportName);
}
