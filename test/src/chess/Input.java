package chess;

import pieces.Piece;

import java.awt.desktop.AboutEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {
    Board board;

    public Input(Board board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.titeSize;
        int row = e.getY() / board.titeSize;

        Piece pieceXY = board.getPiece(col, row);
        if (pieceXY != null) {
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.selectedPiece.xPos = e.getX() - board.titeSize / 2;
            board.selectedPiece.yPos = e.getY() - board.titeSize / 2;

            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.titeSize;
        int row = e.getY() / board.titeSize;
        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.xPos = board.selectedPiece.col * board.titeSize;
                board.selectedPiece.yPos = board.selectedPiece.row * board.titeSize;
            }
        }
        board.selectedPiece = null;
        board.repaint();
    }



}
