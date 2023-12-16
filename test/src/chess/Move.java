package chess;

import pieces.Piece;

public class Move {
    int oldCol;
    int oldRow;
    int newRow;
    int newCol;
    Piece capture;
    Piece piece;
    int evaluation;

    public Move(Board board, Piece piece, int newCol, int newRow) {
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol, newRow);
    }
}
