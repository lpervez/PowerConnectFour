
// TO DO: add your implementation and JavaDocs
/**
 * This is PowerConnectFour Class.
 * @author Laraib Pervez
 *
 */
public class PowerConnectFour {

    // DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW

    /**
     * The grid to contain tokens. Cells can be empty.
     */
    // underlying array of columns for storage -- you MUST use this for credit!
    // Do NOT change the name or type

    private Column<Token>[] grid;

    /**
     * The fixed number of columns the game grid should have.
     */
    private static final int NUM_COLS = 7;

    /**
     * The minimum number of rows of the grid _for display_.
     */
    private static final int MIN_ROWS = 6;

    /**
     * The two players of the game. playerOne is always the first to make a move
     * when the game starts.
     */
    private static final Token playerOne = Token.RED;
    /**
     * Second Player of the game.
     */
    private static final Token playerTwo = Token.YELLOW;

    /**
     * The character used to represent empty cells when the grid is displayed.
     */
    private static final Character empty = Character.valueOf('-');

    /**
     * When grid is displayed, the top row of the grid should always be empty.
     */
    private static final int MARGIN_ROWS = 1;
    /**
     * size.
     */
    private int size;

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
    /**
     * Keeping track of the current player.
     */
    private Token currentPlayer = playerOne;

    /**
     * Constructor.
     */
    @SuppressWarnings("unchecked")
    public PowerConnectFour() {
        // Constructor with no arguments.

        // A grid with NUM_COLS columns should be created. The initial capacity of
        // each column should be DEFAULT_CAPACITY defined in our Column class.
        // All columns are empty initially(size 0).

        // Remember to initialize game settings.
        grid = (Column<Token>[]) new Column[NUM_COLS];
        for (int i = 0; i < NUM_COLS; i++) {
            grid[i] = new Column<Token>(MIN_ROWS);
        }
    }

    /**
     * Private method that will switch player after each turn.
     */
    private void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    /**
     * Return number of columns of the grid.
     *
     * @return Integer
     */
    public int sizeCol() {
        // Return number of columns of the grid
        // Reminder: we set this to be a constant number.

        // O(1)
        return NUM_COLS; // default return, make sure to remove/change
    }

    /**
     * Return number of rows _for DISPLAY_ of the grid.
     *
     * @return Integer
     */
    public int sizeRow() {
        // Return number of rows _for DISPLAY_ of the grid

        // Note: just because a method comes early in the code doesn't mean you should
        // write it first. You need to read the rest of the code to understand how
        // this will work.

        // Reminder: return of this method is used by GUI to set the appropriate display
        // range for the grid.
        // Our rules for display: (check the project spec for details & examples)
        // - always show at least MIN_ROWS for each column;
        // - if the number of pieces of any column reaches or grows beyond MIN_ROWS,
        // make sure the display covers the "tallest" column and leaves one "margin"
        // row at the top of the grid.

        // O(1)
        int largest = grid[0].size();
        for (int i = 1; i < NUM_COLS; i++) {
            if (grid[i].size() > largest) {
                largest = grid[i].size();
            }
        }

        if (largest < MIN_ROWS) {
            return MIN_ROWS;
        }

        return largest + MARGIN_ROWS;
    }

    /**
     * Return the character defined for empty cells for display.
     *
     * @return Character
     */
    public Character getEmptySymbol() {
        // Return the character defined for empty cells for display.
        // Reminder: we set this to be a constant.

        // O(1)
        return empty; // default return, make sure to remove/change
    }

    /**
     * Return token at the given column and row of the grid.
     *
     * @param col column
     * @param row row
     * @return token
     */
    public Token get(int col, int row) {
        // Return token at the given column and row of the grid.

        // For an invalid row/col index (out of the range of current display),
        // throw an IndexOutOfBoundsException.
        // Use this code to produce the correct error message for
        // the exception (do not use a different message):
        // "Col " + col + ", Row "+ row + " out of bounds!"

        // Return null if the cell at the given col and row is empty
        // O(1)
        if (col < 0 && col >= sizeCol() && row < 0) {
            throw new IndexOutOfBoundsException("Col " + col + ", Row " + row + " out of bounds!");
        }

        if (col == 0 || row >= grid[col].size()) {
            return null;
        }


        if (grid[col].get(row) == null) {
            return null;
        }

        return grid[col].get(row);

        // default return, make sure to remove/change
        // Token is stored in a column, and a column is stored in the grid.
        // So if you want to return a token, you would have to access the column in the
        // grid,
        // and that is where the col and row will help you.
    }

    /**
     * Return column at the given index.
     *
     * @param col column
     * @return grid[column]
     */
    public Column<Token> getColumn(int col) {
        // Return column at the given index

        // For an invalid column index, throw an IndexOutOfBoundsException.
        // Use this code to produce the correct error message for
        // the exception (do not use a different message):
        // "Col " + col + " out of bounds!"

        // O(1)

        if (col < 0 || col >= sizeCol()) {
            throw new IndexOutOfBoundsException("Col " + col + " out of bounds!");
        }

        return grid[col]; // default return, make sure to remove/change
    }

    /**
     * Return the player that can make the next move.
     *
     * @return Token
     */
    public Token currentPlayer() {
        // Return the player that can make the next move.
        // O(1)
        return currentPlayer; // default return, make sure to remove/change

    }

    /**
     * Current player drop a token at the given column.
     *
     * @param col column
     * @return boolean
     */
    public boolean drop(int col) {
        // Current player drop a token at the given column.
        // Return true if the move can be made; return false
        // if the move cannot be made for any reason. Switch
        // to the other player only if the move can be made successfully.

        // Reminder: when a column grows, the display settings may need to be changed.

        // Amortized O(1)
        try {
            grid[col].add(currentPlayer());
            switchPlayer();
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Current player drop/insert a token at the given column and row.
     *
     * @param col column
     * @param row row
     * @return boolean
     */
    @SuppressWarnings("unlikely-arg-type")
    public boolean powerDrop(int col, int row) {
        // Current player drop/insert a token at the given column and row.
        // Note: no floating tokens allowed.

        // Return true if the move can be made; return false if the move
        // cannot be made for any reason. Switch to the other player only
        // if the move is made successfully.

        // Reminder: when a column grows, the display settings may need to be changed.

        // O(N) where N is the number of tokens in the involved column

        if ((grid[col].size()) < row) {
            return false;
        }

        try {
            grid[col].add(row, currentPlayer());
            switchPlayer();
            return true;

        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Current player pop a token from the given column.
     *
     * @param col column
     * @return boolean
     */
    @SuppressWarnings("unlikely-arg-type")
    public boolean pop(int col) {
        // Current player pop a token from the given column.
        // Return true if the move can be made; return false
        // if the move cannot be made for any reason. Switch
        // to the other player only if the move is made successfully.

        // Reminder: when a column shrinks, the display settings may need to be changed.

        // O(N) where N is the number of tokens in the involved column
        // if empty

        try {
            if (grid[col].get(0).equals(currentPlayer())) {
                grid[col].delete(0);
                switchPlayer();
                return true;
            } else {
                return false;
            }

        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }

    /**
     * Current player pop/remove a token from the given column and row.
     *
     * @param col column
     * @param row row
     * @return boolean
     */
    @SuppressWarnings("unlikely-arg-type")
    public boolean powerPop(int col, int row) {
        // Current player pop/remove a token from the given column and row.

        // Note: tokens above the removed one need to be shifted to make sure
        // there are no floating tokens in grid.
        // Return true if the move can be made; return false if the move
        // cannot be made for any reason. Switch to the other player only
        // if the move is made successfully.

        // Reminder: when a column shrinks, the display settings may need to be changed.

        // O(N) where N is the number of tokens in the involved column
        if (grid[col].size() == 0) {
            return false;
        }

        try {
            if (grid[col].get(row) != null && grid[col].get(row).equals(currentPlayer())) {
                grid[col].delete(row);
                switchPlayer();
                return true;
            } else {
                return false;
            }

        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Count and return the number of consecutive tokens for the given player.
     *
     * @param col    column
     * @param row    row
     * @param player player
     * @return Integer
     */
    public int countRow(int col, int row, Token player) {
        // Count and return the number of consecutive tokens for the given player
        // in a row such that one of those tokens is at the given row and col
        // of the grid.

        // Return 0 if out of bounds

        // O(1)
        int counter = 1;

        // invalid inputs
        if (col < 1 || col >= sizeCol() || row < 0 || row >= grid[col].size()) {

            return 0;
        }

        if (get(col, row) != player) {
            return 0;
        }

        int columnRight = col + 1;

        if (columnRight < sizeCol()) {
            Token rightColumn = get(columnRight, row);

            while (rightColumn != null && rightColumn == player) {
                counter++;
                columnRight++;
                if (columnRight == sizeCol()) {
                    break;
                }
                rightColumn = get(columnRight, row);
            }
        }


        int columnLeft = col - 1;
        if (columnLeft > 0) {
            Token leftColumn = get(columnLeft, row);

            while (leftColumn != null && leftColumn == player) {
                counter++;
                columnLeft--;
                if (columnLeft < 1) {
                    break;
                }
                leftColumn = get(columnLeft, row);
            }
        }


        return counter; // default return, make sure to remove/change
    }

    /**
     * Count and return the number of consecutive tokens for the given player.
     *
     * @param col    column
     * @param row    row
     * @param player player
     * @return Integer
     */
    public int countCol(int col, int row, Token player) {
        // Count and return the number of consecutive tokens for the given player
        // in a column such that one of those tokens is at the given row and col
        // of the grid.
        // Return 0 if out of bounds
        // O(N) where N is the number of tokens in the involved column

        int counter = 1;

        // invalid inputs
        if (col < 1 || col >= sizeCol() || row < 0 || row >= grid[col].size()) {
            return 0;
        }

        if (get(col, row) != player) {
            return 0;
        }

        int rowUp = row + 1;

        if (rowUp < grid[col].size()) {
            Token upRow = get(col, rowUp);

            while (upRow != null && upRow == player) {
                counter++;
                rowUp++;
                if (rowUp == (sizeRow() - 1)) {
                    break;
                }
                upRow = get(col, rowUp);
            }
        }

        int rowDown = row - 1;

        if (rowDown >= 0) {
            Token downRow = get(col, rowDown);

            while (downRow != null && downRow == player) {
                counter++;
                rowDown--;
                if (rowDown < 0) {
                    break;
                }
                downRow = get(col, rowDown);
            }
        }


        return counter;
        // return -1; //default return, make sure to remove/change
    }

    // Major (\)

    /**
     * Count and return the number of consecutive tokens for the given player.
     *
     * @param col    column
     * @param row    row
     * @param player player
     * @return Integer
     */
    public int countMajorDiagonal(int col, int row, Token player) {
        // Count and return the number of consecutive tokens for the given player
        // in a major diagonal such that one of those tokens is at the given row and col
        // of the grid. A major diagonal line covering (col, row) can extend diagonally
        // down and to the right as well as up and to the left from the given
        // location (col, row).

        // Return 0 if out of bounds
        // O(1)

        int counter = 1;

        // invalid inputs
        if (col < 1 || col >= sizeCol() || row < 0 || row >= grid[col].size()) {
            return 0;
        }

        if (get(col, row) != player) {
            return 0;
        }

        int columnUp = col - 1;
        int rowUp = row + 1;
        if (columnUp > 0 && rowUp < grid[columnUp].size()) {
            Token myPlayer = get(columnUp, rowUp);

            while (myPlayer != null && myPlayer == player) {
                counter++;
                columnUp--;
                rowUp++;
                if (columnUp < 0 || rowUp >= grid[columnUp].size()) {
                    break;
                }
                myPlayer = get(columnUp, rowUp);
            }
        }

        int columnDown = col + 1;
        int rowDown = row - 1;
        if (columnDown < sizeCol() && rowDown >= 0) {
            Token myPlayer = get(columnDown, rowDown);

            while (myPlayer != null && myPlayer == player) {
                counter++;
                columnDown++;
                rowDown--;
                if (columnDown >= sizeCol() || rowDown < 0) {
                    break;
                }
                myPlayer = get(columnDown, rowDown);
            }
        }

        return counter; // default return, make sure to remove/change
    }

    // Minor (/)

    /**
     * Count and return the number of consecutive tokens for the given player.
     *
     * @param col    column
     * @param row    row
     * @param player player
     * @return Integer
     */
    public int countMinorDiagonal(int col, int row, Token player) {
        // Count and return the number of consecutive tokens for the given player
        // in a minor diagonal such that one of those tokens is at the given row and col
        // of the grid. A minor diagonal line covering (col, row) can extend diagonally
        // up and to the right as well as down and to the left from the given
        // location (col, row).

        // Return 0 if out of bounds
        // O(1)
        int counter = 1;

        // invalid inputs
        if (col < 1 || col >= sizeCol() || row < 0 || row >= grid[col].size()) {
            return 0;
        }

        if (get(col, row) != player) {
            return 0;
        }

        int columnUp = col + 1;
        int rowUp = row + 1;
        if (columnUp < sizeCol() && rowUp < grid[columnUp].size()) {
            Token myPlayer = get(columnUp, rowUp);

            while (myPlayer != null && myPlayer == player) {
                counter++;
                columnUp++;
                rowUp++;
                if (columnUp >= sizeCol() || rowUp >= grid[columnUp].size()) {
                    break;
                }
                myPlayer = get(columnUp, rowUp);
            }
        }

        int columnDown = col - 1;
        int rowDown = row - 1;
        if (columnDown > 0 && rowDown >= 0) {
            Token myPlayer = get(columnDown, rowDown);

            while (myPlayer != null && myPlayer == player) {
                counter++;
                columnDown--;
                rowDown--;
                if (columnDown <= 0 || rowDown < 0) {
                    break;
                }
                myPlayer = get(columnDown, rowDown);
            }
        }

        return counter;
    }

    // ******************************************************
    // ******* DO NOT EDIT ANYTHING IN THIS SECTION *******
    // ******* But do read this section! *******
    // ******************************************************

    /**
     * The method that checks whether the specified player has four connected tokens
     * horizontally, vertically, or diagonally. It relies on the methods of
     * countRow(), countCol(), countMajorDiagonal(), and countMinorDiagonal() to
     * work correctly.
     *
     * @param player the token to be checked
     * @return whether the given player has four tokens connected
     */
    public boolean hasFourConnected(Token player) {
        // Check whether the specified player has four tokens either in a row,
        // in a column, or in a diagonal line (major or minor). Return true if
        // so; return false otherwise.

        for (int j = 0; j < sizeCol(); j++) {
            for (int i = 0; i < sizeRow(); i++) {
                if (countRow(j, i, player) >= 4 || countCol(j, i, player) >= 4 || countMajorDiagonal(j, i, player) >= 4
                        || countMinorDiagonal(j, i, player) >= 4)
                    return true;
            }
        }
        return false;

    }

    // ******************************************************
    // ******* BELOW THIS LINE IS TESTING CODE *******
    // ******* Edit it as much as you'd like! *******
    // ******* Remember to add JavaDoc *******
    // ******************************************************

    /**
     * Main Method.
     * @param args args
     */
    public static void main(String[] args) {

        // init with an empty grid
        PowerConnectFour myGame = new PowerConnectFour();
        if (myGame.sizeCol() == NUM_COLS && myGame.sizeRow() == MIN_ROWS && myGame.getColumn(2).size() == 0
                && myGame.currentPlayer() == Token.RED && myGame.get(0, 0) == null) {
            System.out.println("Yay 1!");
        }

        // drop
        if (!myGame.drop(10) && myGame.drop(2) && myGame.getColumn(2).size() == 1 && myGame.get(2, 0) == Token.RED
                && myGame.currentPlayer() == Token.YELLOW) {
            System.out.println("Yay 2!");
        }

        // drop, pop, column growing/shrinking, board display changed
        boolean ok = true;
        for (int i = 0; i < 5; i++) {
            ok = ok && myGame.drop(2); // take turns to drop to column 2 for five times
        }
        // System.out.println("===Current Grid===");
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display
        if (ok && myGame.getColumn(2).size() == 6 && myGame.sizeRow() == 7 && myGame.pop(2) && myGame.sizeRow() == 6
                && myGame.get(2, 1) == Token.RED) {
            System.out.println("Yay 3!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display

        // power drop
        if (!myGame.powerDrop(3, 1) && myGame.powerDrop(3, 0) && myGame.powerDrop(2, 2)
                && myGame.getColumn(2).size() == 6 && myGame.get(2, 2) == Token.RED && myGame.get(2, 3) == Token.YELLOW
                && myGame.getColumn(3).size() == 1) {
            System.out.println("Yay 4!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display

        // power pop
        if (!myGame.powerPop(2, 1) && myGame.powerPop(2, 3) && myGame.getColumn(2).size() == 5
                && myGame.get(2, 3).getSymbol() == 'R') {
            System.out.println("Yay 5!");
        }
        // PowerConnectFourGUI.displayGrid(myGame); //uncomment to check the grid
        // display
        // PowerConnectFourGUI.reportcurrentPlayer(myGame);
        // expected display:
        // | || 0 || 1 || 2 || 3 || 4 || 5 || 6 |
        // | 5 || - || - || - || - || - || - || - |
        // | 4 || - || - || Y || - || - || - || - |
        // | 3 || - || - || R || - || - || - || - |
        // | 2 || - || - || R || - || - || - || - |
        // | 1 || - || - || R || - || - || - || - |
        // | 0 || - || - || Y || Y || - || - || - |
        // Player R's turn

        // counting
        if (myGame.countRow(3, 0, Token.YELLOW) == 2 && myGame.countRow(3, 0, Token.RED) == 0
                && myGame.countCol(2, 3, Token.RED) == 3 && myGame.drop(3) /* one more R */
                && myGame.countMajorDiagonal(3, 1, Token.RED) == 2 /* (3,1) and (2,2) */
                && myGame.countMinorDiagonal(2, 0, Token.YELLOW) == 1) {
            System.out.println("Yay 6!");
        }

    }
}
