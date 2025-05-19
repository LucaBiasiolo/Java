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
    public GoGame save(@RequestBody GoGame goGame){
        return goGameService.save(goGame);
    }
}
