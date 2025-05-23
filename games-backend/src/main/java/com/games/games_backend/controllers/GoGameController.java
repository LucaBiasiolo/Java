package com.games.games_backend.controllers;

import com.games.games_backend.entities.GoGame;
import com.games.games_backend.services.GoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/go")
@CrossOrigin(origins = "http://localhost:4200")
public class GoGameController {

    @Autowired
    private GoGameService goGameService;

    @PostMapping
    public GoGame save(@RequestBody GoGame game){
        return goGameService.save(game);
    }

    @GetMapping
    public Iterable<GoGame> loadGames(){
        return goGameService.loadGames();
    }

    @GetMapping("/{id}")
    public GoGame loadGoGameById(@PathVariable("id") Long gameId){
        return goGameService.loadGoGameById(gameId);
    }

    @DeleteMapping("/{id}")
    public void deleteGoGameById(@PathVariable("id") Long gameId){
        goGameService.deleteGoGameById(gameId);
    }
}
