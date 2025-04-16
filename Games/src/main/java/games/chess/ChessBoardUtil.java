package games.chess;

import games.chess.beans.Move;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains static methods associated to ChessBoard
 */
public class ChessBoardUtil {

    private static final String[] columnIndexToLetter = new String[]{"a","b","c","d","e","f","g","h"};
    private static final Map<String,Integer> letterToColumnIndex = new HashMap<>(8);

    static{
        for (int i = 0; i < columnIndexToLetter.length; i++) {
            letterToColumnIndex.put(columnIndexToLetter[i],i);
        }
    }

    public static Integer convertRowInOtherNotation(Integer startRow){
        return 8-startRow;
    }

    public static Integer fromBoardColumnToMatrixColumn(String columnLetter){
        return letterToColumnIndex.get(columnLetter);
    }

    public static String fromMatrixColumnToBoardColumn(Integer columnIndex){
        return columnIndexToLetter[columnIndex];
    }

    public static Move parsePlayerMoveInBoardCoordinates(String playerMove){
        String startingPosition = playerMove.substring(0,2);
        String endingPosition = playerMove.substring(2,4);

        String startBoardColumn = String.valueOf(startingPosition.charAt(0));
        Integer startBoardRow = Integer.valueOf(startingPosition.substring(1,2));
        String endBoardColumn = String.valueOf(endingPosition.charAt(0));
        Integer endBoardRow = Integer.valueOf(endingPosition.substring(1,2));

        int startMatrixRow = convertRowInOtherNotation(startBoardRow);
        int startMatrixColumn = fromBoardColumnToMatrixColumn(startBoardColumn);
        int endMatrixRow = convertRowInOtherNotation(endBoardRow);
        int endMatrixColumn = fromBoardColumnToMatrixColumn(endBoardColumn);

        return new Move(startMatrixRow, startMatrixColumn, endMatrixRow, endMatrixColumn);
    }
}
