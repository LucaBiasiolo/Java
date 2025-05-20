package com.games.games_backend.services;

import com.games.games_backend.entities.GoGame;
import com.games.games_backend.repositories.GoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoGameService {

    @Autowired
    private GoGameRepository goGameRepository;

    public GoGame save(GoGame goGame) {
        return goGameRepository.save(goGame);
    }

    public Iterable<GoGame> loadGames() {
        return goGameRepository.findAll();
    }

    public GoGame loadGoGameById(long gameId) {
        Optional<GoGame> goGame = goGameRepository.findById(gameId);
        return goGame.orElse(null);
    }

    public void deleteGoGameById(long gameId){
        goGameRepository.deleteById(gameId);
    }
}
