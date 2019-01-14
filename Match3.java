/*
 * Match3
 * Files: Match3.java, Board.java
 *
 * A simple Swing-based game in which the user needs to
 * swap pieces in order to create three in a row (structurally
 * similar to Bejeweled or Candy Crush).
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/////////////////////////////////////////
// class Match3 implements ActionListener
// constructs the GUI and handles user input for the Match3 game
class Match3 implements ActionListener
{
    Board gameboard;
    final int numrows = 10;
    final int numcols = 6;
    boolean pieceSelected = false;
    int selectedRow = -1;
    int selectedCol = -1;
    JButton boardButtons[][];
    JLabel statusBar;
    JButton resetButton;
    JButton quitButton;
    JPanel buttonBar;
    JPanel boardPanel;
    JFrame jfrm;
    final Color DESELECTED_COLOR = Color.LIGHT_GRAY;
    final Color SELECTED_COLOR = Color.YELLOW;
    int points = 0;

    // construct the game board
    Match3() {
        gameboard = new Board(numrows,numcols);


        boardButtons = new JButton[numrows][numcols];
        buttonBar = new JPanel(new FlowLayout());
        resetButton = new JButton("Reset Board");
        quitButton = new JButton("Exit");
        boardPanel = new JPanel(new GridLayout(numrows,numcols,3,3));
        statusBar = new JLabel("Points: " + String.valueOf(points));
        jfrm = new JFrame("Match3");
        
        for(int i=0;i<numrows;i++) {
            for(int j=0;j<numcols;j++) {
                boardButtons[i][j] = new JButton(Board.CELL_LABELS[Board.CELL_EMPTY]);
                boardButtons[i][j].setFont(new Font("Monospaced",Font.PLAIN,20));
                boardButtons[i][j].setActionCommand(
                    String.valueOf(i) + " " + String.valueOf(j));
                boardButtons[i][j].addActionListener(this);
                boardPanel.add(boardButtons[i][j]);
                boardButtons[i][j].setBackground(DESELECTED_COLOR);
                boardButtons[i][j].setOpaque(true);
                boardButtons[i][j].setBorderPainted(false);
            }
        }

        resetButton.addActionListener(this);
        quitButton.addActionListener(this);
        buttonBar.add(resetButton);
        buttonBar.add(quitButton);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.add(statusBar,BorderLayout.SOUTH);
        jfrm.add(buttonBar,BorderLayout.NORTH);
        jfrm.add(boardPanel,BorderLayout.CENTER);
        jfrm.setSize(500,500);
        jfrm.setResizable(false);
        jfrm.setVisible(true);

        reset();
    }

    // reset the board to a random state
    public void reset() {
        gameboard.resetBoard();
        points = 0;
        setStatus("Points: " + String.valueOf(points));
        while(gameboard.existsEmptyCell()) {
            gameboard.dropPieces();
            gameboard.eliminateMatches();
        }
        redrawBoard();
    }

    // change the status bar text
    public void setStatus(String str) {
        statusBar.setText(str);
    }

    // handle button presses
    public void actionPerformed(ActionEvent e) {
        setStatus("Points: " + String.valueOf(points));
        switch(e.getActionCommand()) {
            case "Exit":
                System.exit(0);
            case "Reset Board":
                reset();
                break;
            default: // board piece
                String[] cmd = e.getActionCommand().split(" ");
                int cmdrow = Integer.parseInt(cmd[0]);
                int cmdcol = Integer.parseInt(cmd[1]);
                handleClick(cmdrow,cmdcol);
                break;
        }
    }

    // handle a click event at (row,col)
    public void handleClick(int row,int col) {
        if(!pieceSelected) {
            pieceSelected = true;
            selectedRow = row;
            selectedCol = col;
            boardButtons[row][col].setBackground(SELECTED_COLOR);
            return;
        }

        if(pieceSelected && selectedRow==row && selectedCol==col) {
            pieceSelected = false;
            selectedRow = -1;
            selectedCol = -1;
            boardButtons[row][col].setBackground(DESELECTED_COLOR);
            return;
        }

        // if we get here, then a piece is selected and it is not the
        // newly clicked piece
        if(!(gameboard.isValidSwap(row,col,selectedRow,selectedCol))) {
            setStatus("Invalid move");
            return;
        }
        points++;
        gameboard.makeSwap(row,col,selectedRow,selectedCol);
        boardButtons[selectedRow][selectedCol].setBackground(DESELECTED_COLOR);
        pieceSelected = false;
        selectedRow = -1;
        selectedCol = -1;
        redrawBoard();
        setStatus("Points: " + String.valueOf(points));
    }

    // re-draw the board from underlying game data
    public void redrawBoard() {
        for(int i=0; i<numrows; i++) {
            for(int j=0; j<numcols; j++) {
                boardButtons[i][j].setText(
                   Board.CELL_LABELS[ gameboard.getValueAt(i,j) ]);
            }
        }
    }

    // main
    public static void main(String args[])
    {
        new Match3();
    }
}

