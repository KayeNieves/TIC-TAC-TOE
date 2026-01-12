import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    Color playerXColor = new Color(76, 175, 80);
    Color playerOColor = new Color(244, 67, 54);
    Color tieColor = new Color(255, 152, 0);

    JFrame frame = new JFrame("TIC-TAC-TOE");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton newGameButton = new JButton("R");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.BLACK);
        
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(playerXColor);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("X's TURN");
        textLabel.setOpaque(true);
        
        newGameButton.setText("R");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(playerXColor);
        newGameButton.setFocusable(false);
        newGameButton.setPreferredSize(new java.awt.Dimension(60, 60));
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        
        textPanel.add(textLabel, BorderLayout.CENTER);
        textPanel.add(newGameButton, BorderLayout.EAST);
        
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.WHITE);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.BLACK);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setBorder(BorderFactory.createLineBorder((Color.WHITE), 3));

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            return;
                        }
                        JButton clickedTile = (JButton) e.getSource();
                        if (clickedTile.getText() == "") {
                            clickedTile.setText(currentPlayer);
                            if (currentPlayer == playerX) {
                                clickedTile.setForeground(playerXColor);
                            } else {
                                clickedTile.setForeground(playerOColor);
                            }
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                updateTurnLabel();
                            }
                        }
                    }
                });
            }
        }
    }
    
    void updateTurnLabel() {
        if (currentPlayer.equals(playerX)) {
            textLabel.setForeground(playerXColor);
            textLabel.setText("X's TURN");
        } else {
            textLabel.setForeground(playerOColor);
            textLabel.setText("O's TURN");
        }
    }
    
    void resetGame() {
        gameOver = false;
        currentPlayer = playerX;
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setForeground(Color.WHITE);
            }
        }
        
        updateTurnLabel();
    }
    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (!board[r][0].getText().isEmpty() &&
                board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())) {
                gameOver = true;
                if (currentPlayer.equals(playerX)) {
                    textLabel.setForeground(playerXColor);
                } else {
                    textLabel.setForeground(playerOColor);
                }
                textLabel.setText(currentPlayer + " WINS!");
                board[r][0].setForeground(Color.WHITE);
                board[r][1].setForeground(Color.WHITE);
                board[r][2].setForeground(Color.WHITE);
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (!board[0][c].getText().isEmpty() &&
                board[0][c].getText().equals(board[1][c].getText()) &&
                board[1][c].getText().equals(board[2][c].getText())) {
                gameOver = true;
                if (currentPlayer.equals(playerX)) {
                    textLabel.setForeground(playerXColor);
                } else {
                    textLabel.setForeground(playerOColor);
                }
                textLabel.setText(currentPlayer + " WINS!");
                board[0][c].setForeground(Color.WHITE);
                board[1][c].setForeground(Color.WHITE);
                board[2][c].setForeground(Color.WHITE);
                return;
            }
        }

        if (!board[0][0].getText().isEmpty() &&
            board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText())) {
            gameOver = true;
            if (currentPlayer.equals(playerX)) {
                textLabel.setForeground(playerXColor);
            } else {
                textLabel.setForeground(playerOColor);
            }
            textLabel.setText(currentPlayer + " WINS!");
            board[0][0].setForeground(Color.WHITE);
            board[1][1].setForeground(Color.WHITE);
            board[2][2].setForeground(Color.WHITE);
            return;
        }

        if (!board[0][2].getText().isEmpty() &&
            board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText())) {
            gameOver = true;
            if (currentPlayer.equals(playerX)) {
                textLabel.setForeground(playerXColor);
            } else {
                textLabel.setForeground(playerOColor);
            }
            textLabel.setText(currentPlayer + " WINS!");

            board[0][2].setForeground(Color.WHITE);
            board[1][1].setForeground(Color.WHITE);
            board[2][0].setForeground(Color.WHITE);
            return;
        }

        boolean tie = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().isEmpty()) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            gameOver = true;
            textLabel.setForeground(tieColor);
            textLabel.setText("IT'S A TIE!");
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    board[r][c].setForeground(tieColor);
                }
            }
        }
    }
}