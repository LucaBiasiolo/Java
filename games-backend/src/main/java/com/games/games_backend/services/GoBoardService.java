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
            boardCsv.append("\n");
        }
        return boardCsv.toString();
    }

    public Stone[][] parseBoardCsv(String boardCsv, Integer boardDimension) {
        Stone[][] boardObject = new Stone[boardDimension][boardDimension];
        String[][] boardString = new String[boardDimension][boardDimension];

        String[] boardRows = boardCsv.split("\n");

        for (int i =0; i<boardDimension; i++){
            String[] rowIntersections = boardRows[i].split(",", -1);
            for (int j=0; j<boardDimension; j++) {
                boardString[i][j] = rowIntersections[j];
            }
        }

        for (int i=0; i<boardDimension; i++){
            for (int j=0; j<boardDimension; j++){
                if (boardString[i][j].equals("⚪")){
                    boardObject[i][j] = new Stone(PieceColor.WHITE);
                } else if (boardString[i][j].equals("⚫")){
                    boardObject[i][j] = new Stone(PieceColor.BLACK);
                }
            }
        }
        return boardObject;
    }
}
