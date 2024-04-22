package com.ap.apAssessement.controller;

import com.ap.apAssessement.dto.PlayerDTO;
import com.ap.apAssessement.dto.UpdateSportsRequest;
import com.ap.apAssessement.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/players")
public class PlayersController {

    @Autowired
    PlayersService playersService;

    //Sample call to invoke this API
    //curl -X GET http://localhost:8080/players/getPlayersWithNoSports
    @GetMapping("/getPlayersWithNoSports")
    public ResponseEntity<List<PlayerDTO>> getPlayersWithNoSports() {
        try{
            List<PlayerDTO> playerList = playersService.findPlayersWithNoSports();
            return ResponseEntity.status(HttpStatus.OK).body(playerList);
        }
        catch(Exception e) {
            System.out.println("getPlayersWithNoSports failed with exception - " +  e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Sample call to invoke this API
    //curl -X PUT -H "Content-Type: application/json" -d '{"email": "player1@example.com", "sports": ["soccer","swimming"]}' http://localhost:8080/players/updateSportsForPlayer
    @PutMapping("updateSportsForPlayer")
    public ResponseEntity<?> updateSportsForPlayer(@RequestBody UpdateSportsRequest request) {
        try {
            playersService.updatePlayerSports(request.getEmail(), request.getSports());
            return ResponseEntity.status(HttpStatus.OK).body("Player " + request.getEmail() + " successfully updated with sports " + request.getSports());
        }
        catch(Exception e) {
            System.out.println("Failed to update player's sports: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update player's sports: " + e.getMessage());
        }
    }

    //curl -X POST -H "Content-Type: application/json" -d '["tennis"]' 'http://localhost:8080/players/playersBySports?page=1&size=2'
    @PostMapping("/playersBySports")
    public ResponseEntity<?> getPlayersWithSportsFiltering(
            @RequestBody Set<String> sportNames,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<PlayerDTO> playersPage =  playersService.fetchPlayersBySports(sportNames, PageRequest.of(page, size));
            return ResponseEntity.status(HttpStatus.OK).body(playersPage);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
