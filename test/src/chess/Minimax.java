package chess;

import pieces.*;

public class Minimax extends Piece {
    public static final int maxDepth = 3;
    Piece piece;

    public Minimax(Board board) {
        super(board);
    }

    public Move makeAIMove(Board board, int depth) {
        Move bestMove = minimax(board, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);

        if (bestMove != null && bestMove.piece != null) {
            piece = bestMove.piece;
            board.makeMove(bestMove);
        }

        return bestMove;
    }


    Move minimax(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return new Move(board, null, -1, -1);
        }

        Move[] possibleMoves = board.generateMoves();

        if (maximizingPlayer) {
            Move bestMove = new Move(board, null, -1, -1);
            for (Move move : possibleMoves) {
                if (move.piece == null) {
                    continue;
                }

                board.makeMove(move);
                int eval = minimax(board, depth - 1, alpha, beta, false).evaluation;
                board.undoMove(move);

                if (eval > bestMove.evaluation) {
                    bestMove = new Move(board, move.piece, move.newCol, move.newRow);
                }

                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestMove;
        } else {
            Move bestMove = new Move(board, this, -1, -1);
            for (Move move : possibleMoves) {
                if (move.piece == null) {
                    continue;
                }

                board.makeMove(move);
                int eval = minimax(board, depth - 1, alpha, beta, true).evaluation;
                board.undoMove(move);

                if (eval < bestMove.evaluation) {
                    bestMove = new Move(board, move.piece, move.newCol, move.newRow);
                }

                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestMove;
        }
    }
}

