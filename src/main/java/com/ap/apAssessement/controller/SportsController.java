package com.ap.apAssessement.controller;

import com.ap.apAssessement.dto.SportDTO;
import com.ap.apAssessement.service.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sports")
public class SportsController {

    @Autowired
    SportsService sportsService;

    //Sample call to invoke this API
    //curl -X POST -H "Content-Type: application/json" -d '["tennis", "basketball"]' http://localhost:8080/sports/getSportsWithAssociatedPlayers
    @PostMapping("/getSportsWithAssociatedPlayers")
    public ResponseEntity<List<SportDTO>> getSportsWithAssociatedPlayers(@RequestBody Set<String> sports) {
        try {
            List<SportDTO>  sportsList =  sportsService.findSportsWithAssocPlayers(sports);
            return ResponseEntity.status(HttpStatus.OK).body(sportsList);
        }
        catch(Exception e) {
            System.out.println("getSportsWithAssociatedPlayers failed with exception - " +  e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Sample call to invoke this API
    //curl -X DELETE -H "Content-Type: application/json" -d '["tennis","swimming"]' http://localhost:8080/sports/deleteSports
    @DeleteMapping("/deleteSports")
    public ResponseEntity<String> deleteSports(@RequestBody Set<String> sports) {
        try {
            Set<String> sportsFound = sportsService.deleteSports(sports);
            String message = "Successfully deleted sports - " + sportsFound + ", Sports not found in DB - " + sports;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

