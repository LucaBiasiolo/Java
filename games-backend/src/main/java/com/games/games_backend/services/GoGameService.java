package com.games.games_backend.services;

import com.games.games_backend.entities.GoGame;
import com.games.games_backend.repositories.GoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoGameService {

    @Autowired
    private GoGameRepository goGameRepository;

    public GoGame save(GoGame goGame) {
        return goGameRepository.save(goGame);
    }
}
