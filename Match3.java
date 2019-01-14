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
    JButton boardButtons[][];
    JLabel statusBar;
    JButton resetButton;
    JButton quitButton;
    JPanel buttonBar;
    JPanel boardPanel;
    JFrame jfrm;

    // construct the game board
    Match3() {
        gameboard = new Board(numrows,numcols);


        boardButtons = new JButton[numrows][numcols];
        buttonBar = new JPanel(new FlowLayout());
        resetButton = new JButton("Reset Board");
        quitButton = new JButton("Exit");
        boardPanel = new JPanel(new GridLayout(numrows,numcols));
        statusBar = new JLabel(" ");
        jfrm = new JFrame();
        
        for(int i=0;i<numrows;i++) {
            for(int j=0;j<numcols;j++) {
                boardButtons[i][j] = new JButton(" ");
                boardButtons[i][j].setFont(new Font("Monospaced",Font.PLAIN,20));
                boardButtons[i][j].setActionCommand(
                    String.valueOf(i) + " " + String.valueOf(j));
                boardButtons[i][j].addActionListener(this);
                boardPanel.add(boardButtons[i][j]);
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
    }

    // change the status bar text
    public void setStatus(String str) {
        statusBar.setText(str);
    }

    // handle button presses
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Exit":
                System.exit(0);
            case "Reset Board":
                break;
            default: // board piece
                break;
        }
    }

    // main
    public static void main(String args[])
    {
        new Match3();
    }
}

