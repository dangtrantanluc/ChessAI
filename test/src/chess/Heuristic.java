package chess;

import pieces.Bishop;
import pieces.Piece;
import pieces.Rook;

public class Heuristic extends Piece {
    public Heuristic(Board board) {
        super(board);
    }

    public int getPieceValue(Piece piece) {
        switch (piece.name) {
            case "Pawn":
                return 1;
            case "Rook":
                return 5;
            case "Bishop":
                return 3;
            case "Knight":
                return 3;
            case "Queen":
                return 9;
            case "King":
                return 2000;
            default:
                return 0;
        }
    }

    public int evaluateBoard(Board board) {
        int whiteScore = 0;
        int blackScore = 0;

        for (Piece piece : board.pieceList) {
            int pieceValue = getPieceValue(piece);

            if (piece.isWhite) {
                whiteScore += pieceValue;
                // Ưu tiên kiểm soát trung tâm và biên bàn cờ
                if (piece instanceof Rook) {
                    whiteScore += 1; // Điều chỉnh giá trị cho quân xe (chuyển sang số nguyên)
                }
                if (piece instanceof Bishop) {
                    whiteScore += 1; // Điều chỉnh giá trị cho quân tượng (chuyển sang số nguyên)
                }
            } else {
                blackScore += pieceValue;
                // Ưu tiên kiểm soát trung tâm và biên bàn cờ
                if (piece instanceof Rook) {
                    blackScore += 1; // Điều chỉnh giá trị cho quân xe (chuyển sang số nguyên)
                }
                if (piece instanceof Bishop) {
                    blackScore += 1; // Điều chỉnh giá trị cho quân tượng (chuyển sang số nguyên)
                }
            }
        }

        return Math.abs(whiteScore - blackScore);
    }
}
