        import java.awt.*;
        import java.awt.event.*;

        import javax.management.remote.JMXConnectorServerMBean;
        import javax.swing.*;

        public class TicTacToe {
            

            int boardWidth = 600;
            int boardHeight = 650;
            
            JFrame frame = new JFrame("Tic-Tac-Toe");
            JLabel textLabel = new JLabel();
            JPanel textPanel = new JPanel();
            JPanel boardPanel = new JPanel();
            JPanel buttonPanel = new JPanel();


            JButton[][] board = new JButton[3][3];
            String playerX = "X";
            String playerO = "O";
            String currentPlayer = playerX;


            boolean gameOver = false;
            int moves = 0;

            JButton resetButton;


            TicTacToe() {
                frame.setVisible(true);
                frame.setSize(boardWidth, boardHeight);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                textLabel.setBackground(Color.black);
                textLabel.setForeground(Color.white);
                textLabel.setFont(new Font("Arial", Font.BOLD, 50));
                textLabel.setHorizontalAlignment(JLabel.CENTER);
                textLabel.setText("Tic-Tac-Toe");
                textLabel.setOpaque(true);


                textPanel.setLayout(new BorderLayout());
                textPanel.add(textLabel);
                frame.add(textPanel, BorderLayout.NORTH);

                boardPanel.setLayout(new GridLayout(3, 3));
                boardPanel.setBackground(Color.darkGray);
                frame.add(boardPanel);

                buttonPanel.setLayout(new FlowLayout());
                resetButton = new JButton("Reset Game");
                resetButton.setBackground(Color.black);
                resetButton.setForeground(Color.white);
                resetButton.setFont(new Font("Arial", Font.BOLD, 20));
                resetButton.setFocusable(false);
                resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(); 
            }
        });
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

            
                for (int r = 0; r < 3; r++) {
                    for(int c = 0; c < 3; c++) {
                    JButton tile = new JButton();
                    board[r][c] = tile;
                    boardPanel.add(tile);


                    tile.setBackground(Color.darkGray);
                    tile.setForeground(Color.white);
                    tile.setFont(new Font("Arial", Font.BOLD, 120));
                    tile.setFocusable(false);
                    


                    tile.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            if (gameOver) return;
                            JButton tile = (JButton) e.getSource();

                            if (tile.getText() == "") {

                                tile.setText(currentPlayer);

                                moves++;
                                checkForWinner();
                                if (!gameOver) {

                                    currentPlayer = currentPlayer == playerX ? playerO : playerX;

                                    textLabel.setText(currentPlayer + "'s turn");
                                }

                            }
        
                        }
                    });


                    }
                }

            }

    

        void checkForWinner() {
        
            for (int r = 0; r < 3; r++) {
                if (board[r][0].getText() == "") continue;

                if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {

                    for (int i = 0; i < 3; i++) {
                        placeWinner(board[r][i]);
                    }
                    gameOver = true;
                    return;
                }

            }


            for (int c = 0; c < 3; c++) {
                if (board[0][c].getText() == "") continue;

                if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                    for (int i = 0; i < 3; i++) {
                        placeWinner(board[i][c]);
                    }
                    gameOver = true;
                    return;
                }
            }

                    if (board[0][0].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][2].getText() &&
                    board[0][0].getText() != "") {
                        for (int i = 0; i < 3; i++) {
                            placeWinner(board[i][i]);
                        }
                        gameOver = true;
                        return;
                    }


                    if (board[0][2].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][0].getText() &&
                    board[0][2].getText() != "") {
                        placeWinner(board[0][2]);
                        placeWinner(board[1][1]);
                        placeWinner(board[2][0]);
                        gameOver = true;
                        return;
                    }

                if (moves == 9) {
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            placeTie(board[r][c]);
                        }
                    }
                    gameOver = true;
                }

        }

            void placeWinner(JButton tile) {
                tile.setForeground(Color.green);
                textLabel.setText(currentPlayer + " wins!");
            }

            void placeTie(JButton tile) {
                tile.setForeground(Color.orange);
                textLabel.setText("It's tie!");
            }

            void resetGame() {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        board[r][c].setText("");
                        board[r][c].setForeground(Color.white);
                    }
                }
                currentPlayer = playerX; // reset to player x
                gameOver = false;
                moves = 0;
                textLabel.setText("Tic-Tac-Toe");
            }
        }

