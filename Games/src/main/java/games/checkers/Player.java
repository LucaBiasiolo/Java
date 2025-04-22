package games.checkers;

import games.PieceColor;

public class Player {

    private String name;
    private PieceColor playerColor;
    //private List<Move> moveLog = new ArrayList<>();


    public Player(String name, PieceColor playerColor) {
        this.name = name;
        this.playerColor = playerColor;
    }

    public void movePiece(CheckersBoard board, Piece piece, Direction direction){
        if (piece.getColor().equals(playerColor)){
            int[] pieceCoordinates = board.getPieceCoordinates(piece);
            // todo: handle capturing of piece
            if (piece.getColor().equals(PieceColor.WHITE)){
                switch (direction){
                    case UPPER_RIGHT:
                        if (board.getBoard()[pieceCoordinates[0]-1][pieceCoordinates[1]+1] == null){
                            board.getBoard()[pieceCoordinates[0]][pieceCoordinates[1]] = null;
                            board.getBoard()[pieceCoordinates[0]-1][pieceCoordinates[1]+1] = piece;
                        } else{
                            System.err.println("Cannot move. Square occupied by another piece");
                        }
                        break;
                    case UPPER_LEFT:
                        if (board.getBoard()[pieceCoordinates[0]-1][pieceCoordinates[1]-1] == null){
                            board.getBoard()[pieceCoordinates[0]][pieceCoordinates[1]] = null;
                            board.getBoard()[pieceCoordinates[0]-1][pieceCoordinates[1]-1] = piece;
                        } else{
                            System.err.println("Cannot move. Square occupied by another piece");
                        }
                        break;
                    case LOWER_RIGHT:
                        if (board.getBoard()[pieceCoordinates[0]+1][pieceCoordinates[1]+1] == null){
                            board.getBoard()[pieceCoordinates[0]][pieceCoordinates[1]] = null;
                            board.getBoard()[pieceCoordinates[0]+1][pieceCoordinates[1]+1] = piece;
                        } else{
                            System.err.println("Cannot move. Square occupied by another piece");
                        }
                        break;
                    case LOWER_LEFT:
                        if (board.getBoard()[pieceCoordinates[0]+1][pieceCoordinates[1]-1] == null){
                            board.getBoard()[pieceCoordinates[0]][pieceCoordinates[1]] = null;
                            board.getBoard()[pieceCoordinates[0]+1][pieceCoordinates[1]-1] = piece;
                        } else{
                            System.err.println("Cannot move. Square occupied by another piece");
                        }
                        break;
                }
            }
        } else{
            System.err.println("You cannot move pieces of the other player");
        }
    }
}
