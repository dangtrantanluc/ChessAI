package chess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import pieces.*;

public class ChessGameGUI extends JFrame {
    private Board board;
    private ChessAI ai;
    private boolean isPlayerTurn;

    // Constructor
    public ChessGameGUI() {
        isPlayerTurn = true;
        board = new Board();
        ai = new ChessAI();

        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(board);

// Khởi tạo và thêm nút "Chơi AI" vào cửa sổ
        JButton playAIButton = new JButton("Play with AI");
        playAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức chọn nước của AI khi người chơi nhấn nút "Play with AI"
                playAIMove();
            }
        });

//        this.add(playAIButton);

//        JButton playHumanButton = new JButton("Play with human");
//        playHumanButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Handle the action when the "Play with human" button is clicked
//            }
//        });
//        this.add(playHumanButton);

        // Hiển thị cửa sổ
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        playPlayerTurn();
        playAIMove();
    }

    private void playPlayerTurn() {
        // Lượt của người chơi
        isPlayerTurn = true;
        board.setEnabled(true);
    }

    // Phương thức để thực hiện bước đi của AI
    private void playAIMove() {
        // Lượt của AI
        isPlayerTurn = false;
        // Tắt bảng trong lượt của AI
        board.setEnabled(false);

        // Thực hiện bước đi của AI trên bảng
        Move aiMove = ai.findBestMove(board, 3);
        board.setCurrentAIMove(aiMove);

        // Kiểm tra xem trò chơi đã kết thúc chưa
        if (board.isGameWinner()) {
            JOptionPane.showMessageDialog(null, "Ket thuc tro choi AI thang!");
            System.exit(0);
        }
        board.repaint();
        isPlayerTurn = true;

        playPlayerTurn();
    }

    // Phương thức để xử lý lượt chơi của người chơi dựa trên sự kiện click chuột
    public void handlePlayerTurn(MouseEvent evt) {
        if (isPlayerTurn && board.selectedPiece != null) {
            if (board.selectedPiece != null) {
                int col = evt.getX() / board.titeSize;
                int row = evt.getY() / board.titeSize;

                // Kiểm tra board.selectedPiece có khác null không
                if (board.selectedPiece != null) {
                    Move playerMove = new Move(board, board.selectedPiece, col, row);
                    if (board.isValidMove(playerMove)) {
                        board.makeMove(playerMove);

                        if (board.isGameWinner()) {
                            JOptionPane.showMessageDialog(null, "Ket thuc tro choi nguoi choi thang!");
                            System.exit(0);
                        }
                        board.repaint();
                        isPlayerTurn = false; // Đặt lại lượt chơi cho AI
                        playAIMove();
                    }
                }
            }
        }
    }
}
