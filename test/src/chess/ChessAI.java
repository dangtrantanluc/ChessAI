
package chess;

public class ChessAI  {
    Board board;
    private Minimax chessMinimax;

    public ChessAI() {
        this.chessMinimax = new Minimax(board);
    }

    public Move makeMove(Board board, int depth) {
        return getBestMove(board, depth);
    }

    public Move findBestMove(Board board, int depth) {
        return getBestMove(board, depth);
    }

    private Move getBestMove(Board board, int depth) {
        return chessMinimax.minimax(board, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }

}
