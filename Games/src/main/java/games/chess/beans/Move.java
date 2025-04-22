package games.chess.beans;

import games.chess.ChessBoardUtil;
import games.chess.beans.pieces.Pawn;

public class Move {

    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private ChessPiece piece;
    private boolean isCapture = false;
    private boolean isCheck = false;
    private boolean isCastling =false;
    private Castling castlingType;
    private boolean isCheckMate =false;
    private boolean isPromotion =false;
    private ChessPiece pieceOfPromotion;

    public Move(ChessPiece piece, int startRow, int startColumn, int endRow,int endColumn) {
        this.piece = piece;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public Move(int startRow, int startColumn, int endRow, int endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    public String toBoardNotation(){
        String startBoardColumn = ChessBoardUtil.fromMatrixColumnToBoardColumn(startColumn);
        int startBoardRow = ChessBoardUtil.convertRowInOtherNotation(startRow);
        String endBoardColum = ChessBoardUtil.fromMatrixColumnToBoardColumn(endColumn);
        int endBoardRow = ChessBoardUtil.convertRowInOtherNotation(endRow);

        return String.format("%s%d%s%d",startBoardColumn,startBoardRow,endBoardColum,endBoardRow);
    }

    public String toAlgebraicNotation(){
        if (isCastling){
            return castlingType.getAlgebraicNotation();
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (piece instanceof  Pawn && isCapture){ // if a pawn is capturing add starting file
            stringBuilder.append(startColumn).append("x");
        } else if (!(piece instanceof Pawn)){
            stringBuilder.append(piece.getLetter());
            //todo: insert optional column for disambiguation ?
            if (isCapture) stringBuilder.append("x");
        }

        String endBoardColumn = ChessBoardUtil.fromMatrixColumnToBoardColumn(endColumn);
        int endBoardRow = ChessBoardUtil.convertRowInOtherNotation(endRow);

        stringBuilder.append(endBoardColumn).append(endBoardRow);

        if (isPromotion){
            stringBuilder.append(pieceOfPromotion.getLetter());
        }
        stringBuilder.append(isCheck ? "+" : "");
        stringBuilder.append(isCheckMate ? "#" : "");
        return stringBuilder.toString();
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        isCastling = castling;
    }

    public Castling getCastlingType() {
        return castlingType;
    }

    public void setCastlingType(Castling castlingType) {
        this.castlingType = castlingType;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setIsCapture(boolean capture) {
        isCapture = capture;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
