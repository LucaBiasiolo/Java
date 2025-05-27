package com.games.games_backend.services;

import com.games.games_backend.entities.GoBoard;
import com.games.games_backend.entities.PieceColor;
import com.games.games_backend.entities.Stone;
import org.springframework.stereotype.Service;

@Service
public class GoBoardService {

    public String translateBoardIntoCsv(GoBoard goBoard){
        StringBuilder boardCsv = new StringBuilder();
        Stone[][] board = goBoard.getBoard();
        for (Stone[] boardRow : board){
            for(Stone boardIntersection: boardRow){
                if (boardIntersection == null){
                    boardCsv.append(",");
                } else{
                    if (boardIntersection.getColor().equals(PieceColor.WHITE)) {
                        boardCsv.append("⚪,");
                    } else{
                        boardCsv.append("⚫,");
                    }
                }
            }
        }
        return boardCsv.toString();
    }
}
