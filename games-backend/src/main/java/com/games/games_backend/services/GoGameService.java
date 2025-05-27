package com.games.games_backend.services;

import com.games.games_backend.entities.GoGame;
import com.games.games_backend.entities.Move;
import com.games.games_backend.repositories.GoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoGameService {

    @Autowired
    private GoGameRepository goGameRepository;
    @Autowired
    private GoBoardService goBoardService;

    public GoGame save(GoGame goGame) {
        for (Move move : goGame.getMoves()){
            move.setGame(goGame);
        }
        goGame.getGoBoard().setBoardCsv(goBoardService.translateBoardIntoCsv(goGame.getGoBoard()));
        return goGameRepository.save(goGame);
    }

    public Iterable<GoGame> findGamesOrderedByCreatedAtDesc() {
        Iterable<GoGame> gamesOrderedByCreatedAtDesc = goGameRepository.findAllByOrderByCreatedAtDesc();
        for (GoGame game : gamesOrderedByCreatedAtDesc){
            game.getGoBoard().setBoard(goBoardService.parseBoardCsv(game.getGoBoard().getBoardCsv(), game.getGoBoard().getBoardDimension()));
        }
        return gamesOrderedByCreatedAtDesc;
    }

    public GoGame loadGoGameById(long gameId) {
        Optional<GoGame> goGame = goGameRepository.findById(gameId);
        return goGame.orElse(null);
    }

    public void deleteGoGameById(long gameId){
        goGameRepository.deleteById(gameId);
    }
}
