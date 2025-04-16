package games.chess.beans;

import static games.chess.ChessBoard.*;

public class Move {

    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private ChessPiece piece;
    private boolean isCapture;
    private boolean isCheck;
    private boolean isCastling; // todo: distinguish betwenn king-side and queen-side castling
    private boolean isCheckMate;
    private boolean isPromotion;

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
        String startBoardColumn = getBoardColumnFromColumnIndex(startColumn);
        int startBoardRow = getBoardRowFromRowIndex(startRow);
        String endBoardColum = getBoardColumnFromColumnIndex(endColumn);
        int endBoardRow = getBoardRowFromRowIndex(endRow);

        return String.format("%s%d%s%d",startBoardColumn,startBoardRow,endBoardColum,endBoardRow);
    }

    public String toAlgebraicNotation(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(piece.getLetter());
        //todo: insert optional column for disambiguation ?
        stringBuilder.append(isCapture ? "x" : "");

        String endBoardColumn = getBoardColumnFromColumnIndex(endColumn);
        int endBoardRow = getBoardRowFromRowIndex(endRow);

        stringBuilder.append(endBoardColumn).append(endBoardRow);
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
}
