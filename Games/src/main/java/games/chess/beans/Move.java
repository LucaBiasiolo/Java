package games.chess.beans;

import games.chess.ChessBoardUtil;

public class Move {

    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private ChessPiece piece;
    private boolean isCapture;
    private boolean isCheck;
    private boolean isCastling; // todo: distinguish betwenn king-side and queen-side castling
    private Castling castlingType;
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
        String startBoardColumn = ChessBoardUtil.fromMatrixColumnToBoardColumn(startColumn);
        int startBoardRow = ChessBoardUtil.fromMatrixRowToBoardRow(startRow);
        String endBoardColum = ChessBoardUtil.fromMatrixColumnToBoardColumn(endColumn);
        int endBoardRow = ChessBoardUtil.fromMatrixRowToBoardRow(endRow);

        return String.format("%s%d%s%d",startBoardColumn,startBoardRow,endBoardColum,endBoardRow);
    }

    public String toAlgebraicNotation(){
        if (isCastling){
            return castlingType.getAlgebraicNotation();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(piece.getLetter());
        //todo: insert optional column for disambiguation ?
        stringBuilder.append(isCapture ? "x" : "");

        String endBoardColumn = ChessBoardUtil.fromMatrixColumnToBoardColumn(endColumn);
        int endBoardRow = ChessBoardUtil.fromMatrixRowToBoardRow(endRow);

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
}
