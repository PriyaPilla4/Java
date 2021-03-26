public class BoardChecker {
    public static final int OPPONENT_WIN = -1;
    public static final int TIE = 0;
    public static final int AI_WIN = 1;
    public static final int NO_WINNER_YET = 2;

    public BoardChecker(int boardSize, int numDiskToConnect, String myMark, String aiMark) {
        this.boardSize = boardSize;
        this.numDiskToConnect = numDiskToConnect;
        this.myMark = myMark;
        this.aiMark = aiMark;
    }

    public int checkWin(final Board board) {

        int x; // row
        int y; // col
    
        /*
         * The goal is to connect M disks of the same color contiguously across the rows
         * and columns in the grid either horizontally, vertically, or diagonally. The
         * first player that connects all M disks contiguously wins the game.
         */

        x = 0;
        y = 0;

        while (x <= boardSize - 1) { // rows
            y = 0;

            while (y <= boardSize - 1) { // colums

                if (board.getCell(x, y).isPicked() == true) {

                    String currentCell = board.getCell(x, y).getContent(); // get the symbol in the cell

                    // check horizontal
                    checkHorizontal(x, y, board, currentCell);

                    // check vertical
                    checkVertical(x, y, board, currentCell);

                    // check right diagonal
                    checkRightDiagonal(x, y, board, currentCell);

                    // check left diagonal
                    checkLeftDiagonal(x, y, board, currentCell);

                }
                y++;
            }
            x++;
        }

        return Integer.MAX_VALUE; // return no winner yet
    }

    public void checkHorizontal(int x, int y, Board board, String currentCell) {
        // do a right check
        int i = 1;

        if (y + (numDiskToConnect - 1) <= boardSize - 1) { // enough right cells exists for matching

            while (i <= (numDiskToConnect - 1)) { // check right cells until numDiskToConnect

                if (board.getCell(x, y + i).isPicked() == true) { // right cell has symbol in it

                    String rightCell = board.getCell(x, y + i).getContent();
                   

                    if (!currentCell.equals(rightCell)) { // if right cells doesn't have the same symbol, break
                      
                        break;
                    }

                } else {
                   
                    break;
                }
                i++;
            }

        }

        // check winner
        if (i == numDiskToConnect) {
            board.printBoard();
            getWinner(currentCell);
        }
    }

    public void checkVertical(int x, int y, Board board, String currentCell) {
        // do a bottom check
        int i = 1;

        if (x + (numDiskToConnect - 1) <= boardSize - 1) { // enough bottom cells exists for matching

            while (i <= (numDiskToConnect - 1)) { // check bottom cells until numDiskToConnect

                if (board.getCell(x + i, y).isPicked() == true) { // bottom cell has symbol in it

                    String bottomCell = board.getCell(x + i, y).getContent();
                   
                    if (!currentCell.equals(bottomCell)) { // if bottom cells doesn't have the same symbol, break
                        break;
                    }

                } else {
                  
                    break;
                }
                i++;
            }
        }

        // check winner
        if (i == numDiskToConnect) {
            board.printBoard();
            getWinner(currentCell);
        }
    }

    public void checkRightDiagonal(int x, int y, Board board, String currentCell) {
        // do a right diagonal check
        int i = 1;
        // Enough right diagonal cells exists for matching
        if (x + (numDiskToConnect - 1) <= boardSize - 1 && y + (numDiskToConnect - 1) <= boardSize - 1) {

            while (i <= (numDiskToConnect - 1)) { // check right diagonal cells until numDiskToConnect

                if (board.getCell(x + i, y + i).isPicked() == true) { // right diagonal cell has symbol in it

                    String rightDiagonalCell = board.getCell(x + i, y + i).getContent();
                    // System.out.println("right diagonal cell content: " + rightDiagonalCell);

                    if (!currentCell.equals(rightDiagonalCell)) { // if right diagonal cells doesn't have the same
                        break;
                    }

                } else {
                    break;
                }
                i++;
            }

        }

        // check winner
        if (i == numDiskToConnect) {
            board.printBoard();
            getWinner(currentCell);
        }
    }

    public void checkLeftDiagonal(int x, int y, Board board, String currentCell) {
        // do a left diagonal check
        int i = 1;

        if (x + (numDiskToConnect - 1) <= boardSize - 1 && y - (numDiskToConnect - 1) >= 0
                && y - (numDiskToConnect - 1) <= boardSize - 1) { // enough left diagonal cells exists for matching

            while (i <= (numDiskToConnect - 1)) { // check left diagonal cells until numDiskToConnect

                if (board.getCell(x + i, y - i).isPicked() == true) { // left diagonal cell has symbol in it

                    String leftDiagonalCell = board.getCell(x + i, y - i).getContent();
                    // System.out.println("left diagonal cell content: " + leftDiagonalCell);

                    if (!currentCell.equals(leftDiagonalCell)) { // if left diagonal cells doesn't have the same symbol,
                                                                 // break
                        // System.out.println("left diagonal cell doesnt have same symbol");
                        break;
                    }

                } else {
                    // System.out.println("left diagonal cell doesnt have symbol");
                    break;
                }
                i++;
            }

        }

        // check winner
        if (i == numDiskToConnect) {
            board.printBoard();
            getWinner(currentCell);
        }
    }

    public void getWinner(String currentCell) {
        // get winner

        // System.out.println("ITS A WINNER");
        if (currentCell.equals(myMark)) {
            System.out.println("You Won!, AI lost.");
            System.exit(OPPONENT_WIN);
            // return OPPONENT_WIN;
        }

        if (currentCell.equals(aiMark)) {
            System.out.println("You lost :(, AI won.");
            System.exit(AI_WIN);
            // return AI_WIN;
        }
    }

    public int checkTie(final Board board) {

        /*
         * goes through the cells and checks if there's still an empty spot, if not, its
         * a tie
         */

        int x; // row
        int y; // col

        x = 0;
        y = 0;

        while (x <= boardSize - 1) { // rows
            y = 0;

            while (y <= boardSize - 1) { // colums

                if (!(board.getCell(x, y).isPicked() == true)) { // empty cell found
                    return NO_WINNER_YET;
                }
                y++;
            }
            x++;
        }

        System.out.println("Its a tie!");
        System.exit(TIE);
        return TIE;
    }

    int boardSize;
    int numDiskToConnect;
    String myMark;
    String aiMark;
}
