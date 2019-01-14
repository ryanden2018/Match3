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


    // drop fresh candy from the top until board is full
    public void drop() {
    }
}

