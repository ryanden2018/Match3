import java.util.*;

// class Board
// underlying game engine for Match3
class Board
{
    static final int CELL_EMPTY = 0;   // empty cell
    static final int CELL_X = 1;       // X
    static final int CELL_STAR = 2;    // *
    static final int CELL_O = 3;       // O
    static final int CELL_DIAMOND = 4; // <>
    static final int CELL_BOX = 5;     // []
    static final String[] CELL_LABELS = {" ","X","*","O","<>","[]"};
    static final int CELL_MIN = CELL_X;
    static final int CELL_MAX = CELL_BOX;
    int numrows,numcols;
    int[][] board;

    // create an empty board
    Board(int numrows, int numcols)
    {
        this.numrows = numrows;
        this.numcols = numcols;
        board = new int[numrows][numcols];
        for(int i=0;i < numrows; i++) {
            for(int j=0;j < numcols; j++) {
                board[i][j] = CELL_EMPTY;
            }
        }
    }

    // construct a copy of an existing board
    Board(Board oldboard) {
        numrows = oldboard.getNumRows();
        numcols = oldboard.getNumCols();
        board = new int[numrows][numcols];
        for(int i=0;i < numrows; i++) {
            for(int j=0;j < numcols; j++) {
                board[i][j] = oldboard.board[i][j];
            }
        }
    }

    public int getNumRows() {
        return numrows;
    }

    public int getNumCols() {
        return numcols;
    }

    // set the entire board to empty cells
    public void resetBoard() {
        for(int i=0;i < numrows; i++) {
            for(int j=0;j < numcols; j++) {
                board[i][j] = CELL_EMPTY;
            }
        }
    }

    public int getValueAt(int row, int col) {
        return board[row][col];
    }

    // let all pieces fall to the bottom under gravity, then let in
    // new ones from the top drawn randomly
    public void dropPieces() {
        // work column by column
        for(int j=0; j<numcols; j++) {
            int[] thiscol = new int[numrows];
            for(int i=0;i<numrows;i++) {
                thiscol[i] = CELL_EMPTY;
            }
            
            int target_index = numrows - 1;
            for(int i=numrows-1; i>=0; i--) {
                if(board[i][j] != CELL_EMPTY) {
                    thiscol[target_index] = board[i][j];
                    target_index--;
                }
            }

            while(target_index >= 0) {
                thiscol[target_index] = new Random().nextInt(CELL_MAX)+1;
                target_index--;
            }

            for(int i=0;i<numrows;i++) {
                board[i][j] = thiscol[i];
            }
        }
    }

    // check if there exists an empty cell
    public boolean existsEmptyCell() {
        boolean empty_cell_found = false;

        for(int i=0;i < numrows; i++) {
            for(int j=0;j < numcols; j++) {
                if(board[i][j] == CELL_EMPTY) {
                    empty_cell_found = true;
                }
            }
        }
        
        return empty_cell_found;
    }

    // eliminate any 3 consecutive matching pieces
    public void eliminateMatches() {
        for(int i=0;i<numrows;i++) {
            for(int j=0;j<numcols;j++) {
                // 3 in a row
                if(0<j && j<numcols-1) {
                    if(board[i][j-1] == board[i][j] &&
                                board[i][j+1] == board[i][j]) {
                        board[i][j] = CELL_EMPTY;
                        board[i][j-1] = CELL_EMPTY;
                        board[i][j+1] = CELL_EMPTY;
                    }
                }

                // 3 in a col
                if(0<i && i<numrows-1) {
                    if(board[i-1][j] == board[i][j] &&
                                board[i+1][j] == board[i][j]) {
                        board[i][j] = CELL_EMPTY;
                        board[i-1][j] = CELL_EMPTY;
                        board[i+1][j] = CELL_EMPTY;
                    }
                }
            }
        }
    }
}

