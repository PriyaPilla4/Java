
import java.util.Random;

public class MoveEvaluator {

    public MoveEvaluator(int numDiskCon, int turn, String opponentMark, String aiMark) {
        this.numDiskToConnect = numDiskCon; // The number of disks that must be connected contiguously
        this.aiTurn = (turn == 0) ? true : false;
        this.opponentMark = opponentMark;
        this.aiMark = aiMark;
        // this.boardCheck = new BoardChecker();
    }

    private int minValue(final Board board, int depth, int alpha, int beta) {
        // Board state = new Board(boardState);
        // System.out.println("In minValue: depth is " + depth);
        // if (boardCheck.checkWin(board) == BoardChecker.OPPONENT_WIN) {
        // return Integer.MIN_VALUE;
        // }
        if (depth == 0) {
            return this.utility(board);
        }
        --depth;

        for (int selectedCol = 0; selectedCol < board.getBoardSize(); ++selectedCol) {
            Board boardState = new Board(board);
            beta = Math.min(beta, maxValue(applyMove(boardState, selectedCol, aiMark), depth, alpha, beta));
            // System.out.println("beta is: " + beta);
            if (alpha >= beta) { // Opponent wins
                return Integer.MIN_VALUE;
            }
        }
        return beta;
    }

    private int maxValue(final Board board, int depth, int alpha, int beta) {
        // System.out.println("In maxValue: depth is " + depth);
        // if (boardCheck.checkWin(board) == BoardChecker.AI_WIN) {
        // return Integer.MAX_VALUE;
        // }
        if (depth == 0) {
            return this.utility(board);
        }
        --depth;

        for (int selectedCol = 0; selectedCol < board.getBoardSize(); ++selectedCol) {
            Board boardState = new Board(board);
            alpha = Math.max(alpha, minValue(applyMove(boardState, selectedCol, opponentMark), depth, alpha, beta));
            if (alpha >= beta) { // Opponent wins
                return Integer.MAX_VALUE;
            }
        }
        return alpha;
    }

    private Board applyMove(Board board, int move, String mark) {
        board.markMove(move, mark);
        // System.out.println("--------- In applyMove() ----------");
        // board.printBoard();
        return board;
    }

    private int utility(final Board state) {
        // System.out.println("From horizontal: " + horizontal(state));
        // System.out.println("From vertical: " + vertical(state));
        return vertical(state) + horizontal(state) + diagonalSE(state) + diagonalSW(state);
    }

    // Assume the board passed in with the opponent's move made at that specifically
    // cell
    public int vertical(final Board board) {
        int opponentNumWaysToWin = 0, aiNumWaysToWin = 0;
        int tempOpponentStreak = 0, tempAiStreak = 0;
        int opponentMaxStreak = 0, aiMaxStreak = 0;
        int[] movesMadeByCol = board.getMovesMadeByColumn();

        // System.out.println("----------- In vertical ------------");
        // board.printBoard();

        for (int j = 0; j < board.getBoardSize(); ++j) {
            // Checking the total number of same disks in a row
            int currIndex = movesMadeByCol[j] + 1;
            int numMovesLeft = movesMadeByCol[j] + 1;
            if (currIndex == board.getBoardSize()) { // Blank column
                ++opponentNumWaysToWin;
                ++aiNumWaysToWin;
                continue;
            }

            for (int i = currIndex; i < board.getBoardSize(); ++i) { // Check opponent's contiguous disks
                if (board.getCell(i, j).getContent().equals(opponentMark)) {
                    ++tempOpponentStreak;
                } else {
                    break;
                }
            }
            if ((tempOpponentStreak + numMovesLeft) >= this.numDiskToConnect) {
                ++opponentNumWaysToWin;
            }
            opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
            tempOpponentStreak = 0;

            for (int i = currIndex; i < board.getBoardSize(); ++i) { // Check AI's contiguous disks
                if (board.getCell(i, j).getContent().equals(aiMark)) {
                    ++tempAiStreak;
                } else {
                    break;
                }
            }
            if ((tempAiStreak + numMovesLeft) >= this.numDiskToConnect) {
                ++aiNumWaysToWin;
            }
            aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Max contiguous number of same disk.
            tempAiStreak = 0;
        }

        // System.out.println("Opponent streak: " + opponentMaxStreak + " | AI streak: "
        // + aiMaxStreak);
        // System.out.println("Opponent num ways win: " + opponentNumWaysToWin + " | AI
        // num ways win: " + aiNumWaysToWin);
        // System.out.println("Opponent mark: " + this.opponentMark);

        if (aiMaxStreak == this.numDiskToConnect) {
            return Integer.MAX_VALUE;
        } else if (opponentMaxStreak == (this.numDiskToConnect - 1)) {
            return Integer.MIN_VALUE;
        } else {
            return aiNumWaysToWin - opponentNumWaysToWin;
            // return (aiMaxStreak * opponentNumWaysToWin) - (opponentMaxStreak *
            // aiNumWaysToWin);
        }
    }

    public int horizontal(final Board board) {
        int opponentNumWaysToWin = 0, aiNumWaysToWin = 0;
        int tempOpponentStreak = 1, tempAiStreak = 1; // Set to because we're also looking ahead.
        int opponentMaxStreak = 0, aiMaxStreak = 0;
        int numOpponentMarks = 0, numAiMarks = 0;
        Cell cell = null, nextCell = null;
        int opponentIndex = 0, aiIndex = 0; // Indicate the the column
        // Pair opponentPair = new Pair(); // To hold the opponent's possible streak and
        // number of marks
        // Pair aiPair = new Pair(); // To hold the AI's possible streak and number of
        // marks
        // System.out.println("----------- In horizontal ------------");
        // board.printBoard();

        for (int i = board.getBoardSize() - 1; i >= 0; --i) { // Count the number of ways to win for AI and opponent.
            for (int j = 0; j < board.getBoardSize() - 1; ++j) {
                cell = board.getCell(i, j);
                nextCell = board.getCell(i, j + 1);
                // If the current cell and the next cell is marked by opponent or a blank cell,
                // increment opponent's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.opponentMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.opponentMark))) {
                    ++tempOpponentStreak;
                    opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
                } else {
                    tempOpponentStreak = 1;
                }
                // If the current cell and the next cell is marked by AI or a blank cell,
                // increment AI's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.aiMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.aiMark))) {
                    ++tempAiStreak;
                    aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Calculate the max number of streak.
                } else {
                    tempAiStreak = 1;
                }
                // if (opponentMaxStreak >= this.numDiskToConnect) { // new
                // opponentIndex = j + 1;
                // }
                // if (aiMaxStreak >= this.numDiskToConnect) { // new
                // aiIndex = j + 1;
                // }
            }
            if (opponentMaxStreak >= this.numDiskToConnect) { // If there is a way to win for opponent in this row
                ++opponentNumWaysToWin;
                // Extract number of opponent's marks in this streak
                // for (int k = opponentIndex; k >= (board.getBoardSize() - opponentMaxStreak);
                // --k) { // new
                // if (board.getCell(i, k).getContent().equals(this.opponentMark)) {
                // ++numOpponentMarks;
                // }
                // }
            }
            if (aiMaxStreak >= this.numDiskToConnect) { // If there is a way to win for AI in this row
                ++aiNumWaysToWin;
                // Extract number of opponent's marks in this streak
                // for (int k = aiIndex; k >= (board.getBoardSize() - aiMaxStreak); --k) { //
                // new
                // System.out.println("i is " + i + " | k is " + k);
                // if (board.getCell(i, k).getContent().equals(this.aiMark)) {
                // ++numAiMarks;
                // }
                // }
            }
            int tempOpponentNumOfMarks = 0;
            int tempAiNumOfMarks = 0;
            for (int k = 0; k < board.getBoardSize(); ++k) { // Horizontal streak
                if (board.getCell(i, k).getContent().equals(this.opponentMark)) {
                    ++tempOpponentNumOfMarks;
                    numOpponentMarks = Math.max(numOpponentMarks, tempOpponentNumOfMarks);
                } else {
                    tempOpponentNumOfMarks = 0;
                }

                if (board.getCell(i, k).getContent().equals(this.aiMark)) {
                    ++tempAiNumOfMarks;
                    numAiMarks = Math.max(numAiMarks, tempAiNumOfMarks);
                } else {
                    tempAiNumOfMarks = 0;
                }
            }
            // System.out.println("Max streak opponent: " + opponentMaxStreak + " | max
            // streak AI: " + aiMaxStreak);
            tempAiStreak = 1;
            tempOpponentStreak = 1;
            opponentMaxStreak = 0;
            aiMaxStreak = 0;

        }

        // Use pair to store the potential streak and the total number of the same mark
        // in that streak.
        // System.out.println("Num opp marks: " + numOpponentMarks + " | Num AI marks: "
        // + numAiMarks);
        // System.out.println("opponent mark: " + this.opponentMark);
        // System.out.println("Opponent points: " + opponentNumWaysToWin + " | AI
        // points: " + aiNumWaysToWin);
        if (numAiMarks == this.numDiskToConnect) {
            return Integer.MAX_VALUE;
        } else if (numOpponentMarks == (this.numDiskToConnect - 1)) {
            return Integer.MIN_VALUE;
        } else {
            return (opponentNumWaysToWin * numOpponentMarks - aiNumWaysToWin * numAiMarks);
        }
        // return opponentPoints;
    }

    public int diagonalSE(final Board board) {
        int opponentNumWaysToWin = 0, aiNumWaysToWin = 0;
        int tempOpponentStreak = 1, tempAiStreak = 1; // Set to because we're also looking ahead.
        int opponentMaxStreak = 0, aiMaxStreak = 0;
        int opponentNumMarks = 0, aiNumMarks = 0;
        Cell cell = null, nextCell = null;

        // Bottom left start from mid way--------------------------------------------
        for (int k = 0; k < board.getBoardSize(); ++k) {
            int spacesToLoopDiag = board.getBoardSize() - k;
            if (spacesToLoopDiag < this.numDiskToConnect) {
                break;
            }

            for (int j = 0; j < spacesToLoopDiag; ++j) {
                int i = j + k;
                cell = board.getCell(i, j);
                if ((i + 1) == board.getBoardSize() || (j + 1) == board.getBoardSize()) {
                    break;
                }
                nextCell = board.getCell(i + 1, j + 1);
                // If the current cell and the next cell is marked by opponent or a blank cell,
                // increment opponent's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.opponentMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.opponentMark))) {
                    ++tempOpponentStreak;
                    opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
                } else {
                    tempOpponentStreak = 1;
                }
                // If the current cell and the next cell is marked by AI or a blank cell,
                // increment AI's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.aiMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.aiMark))) {
                    ++tempAiStreak;
                    aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Calculate the max number of streak.
                } else {
                    tempAiStreak = 1;
                }
            }
            if (opponentMaxStreak >= this.numDiskToConnect) { // If there is a way to win for opponent in this diagonal
                ++opponentNumWaysToWin;
            }
            if (aiMaxStreak >= this.numDiskToConnect) { // If there is a way to win for AI in this diagonal
                ++aiNumWaysToWin;
            }
            // System.out.println("Max streak opponent: " + opponentMaxStreak + " | max
            // streak AI: " + aiMaxStreak);
            tempAiStreak = 1;
            tempOpponentStreak = 1;
            opponentMaxStreak = 0;
            aiMaxStreak = 0;
        }
        // System.out.println("Opponent ways to win: " + opponentNumWaysToWin + " | AI
        // ways to win: " + aiNumWaysToWin);

        // Upper right --------------------------------------------
        for (int k = 0; k < board.getBoardSize(); ++k) {
            int spacesToLoopDiag = board.getBoardSize() - k - 1;
            if (spacesToLoopDiag < this.numDiskToConnect) {
                break;
            }

            for (int i = 0; i < spacesToLoopDiag; ++i) {
                int j = k + i + 1;
                cell = board.getCell(i, j);
                if ((i + 1) == board.getBoardSize() || (j + 1) == board.getBoardSize()) {
                    break;
                }
                nextCell = board.getCell(i + 1, j + 1);
                // If the current cell and the next cell is marked by opponent or a blank cell,
                // increment opponent's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.opponentMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.opponentMark))) {
                    ++tempOpponentStreak;
                    opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
                } else {
                    tempOpponentStreak = 1;
                }
                // If the current cell and the next cell is marked by AI or a blank cell,
                // increment AI's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.aiMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.aiMark))) {
                    ++tempAiStreak;
                    aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Calculate the max number of streak.
                } else {
                    tempAiStreak = 1;
                }
            }
            if (opponentMaxStreak >= this.numDiskToConnect) { // If there is a way to win for opponent in this diagonal
                ++opponentNumWaysToWin;
            }
            if (aiMaxStreak >= this.numDiskToConnect) { // If there is a way to win for AI in this diagonal
                ++aiNumWaysToWin;
            }
            // System.out.println("Max streak opponent: " + opponentMaxStreak + " | max
            // streak AI: " + aiMaxStreak);
            tempAiStreak = 1;
            tempOpponentStreak = 1;
            opponentMaxStreak = 0;
            aiMaxStreak = 0;
        }
        // System.out.println("Opponent ways to win: " + opponentNumWaysToWin + " | AI
        // ways to win: " + aiNumWaysToWin);

        if (aiMaxStreak == this.numDiskToConnect) {
            return Integer.MAX_VALUE;
        } else if (opponentMaxStreak == (this.numDiskToConnect - 1)) {
            return Integer.MIN_VALUE;
        } else {
            return aiNumWaysToWin - opponentNumWaysToWin;
            // return (aiMaxStreak * opponentNumWaysToWin) - (opponentMaxStreak *
            // aiNumWaysToWin);
        }
    }

    public int diagonalSW(final Board board) {
        int opponentNumWaysToWin = 0, aiNumWaysToWin = 0;
        int tempOpponentStreak = 1, tempAiStreak = 1;
        int opponentMaxStreak = 0, aiMaxStreak = 0;
        Cell cell = null, nextCell = null;

        // Bottom right --------------------------------------------
        for (int k = 1; k < board.getBoardSize(); ++k) {
            if ((board.getBoardSize() - k) < this.numDiskToConnect) {
                break;
            }
            int j = board.getBoardSize() - 1;

            for (int i = k; i < board.getBoardSize(); ++i) {
                cell = board.getCell(i, j);
                if ((i + 1) == board.getBoardSize() || (j - 1) < 0) {
                    break;
                }
                nextCell = board.getCell(i + 1, j - 1);
                // If the current cell and the next cell is marked by opponent or a blank cell,
                // increment opponent's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.opponentMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.opponentMark))) {
                    ++tempOpponentStreak;
                    opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
                } else {
                    tempOpponentStreak = 1;
                }
                // If the current cell and the next cell is marked by AI or a blank cell,
                // increment AI's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.aiMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.aiMark))) {
                    ++tempAiStreak;
                    aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Calculate the max number of streak.
                } else {
                    tempAiStreak = 1;
                }
                --j;
            }
            if (opponentMaxStreak >= this.numDiskToConnect) { // If there is a way to win for opponent in this diagonal
                ++opponentNumWaysToWin;
            }
            if (aiMaxStreak >= this.numDiskToConnect) { // If there is a way to win for AI in this diagonal
                ++aiNumWaysToWin;
            }
            // System.out
            // .println("Opponent ways to win: " + opponentNumWaysToWin + " | AI ways to
            // win: " + aiNumWaysToWin);
            // System.out.println("Max streak opponent: " + opponentMaxStreak + " | max
            // streak AI: " + aiMaxStreak);
            tempAiStreak = 1;
            tempOpponentStreak = 1;
            opponentMaxStreak = 0;
            aiMaxStreak = 0;
        }

        // Upper left starts at mid way --------------------------------------------
        for (int k = board.getBoardSize() - 1; k >= 0; --k) {
            if ((k + 1) < this.numDiskToConnect) {
                break;
            }
            for (int i = 0; i <= k; ++i) {
                int j = k - i;
                cell = board.getCell(i, j);
                if ((i + 1) == board.getBoardSize() || (j - 1) < 0) {
                    break;
                }
                nextCell = board.getCell(i + 1, j - 1);
                // If the current cell and the next cell is marked by opponent or a blank cell,
                // increment opponent's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.opponentMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.opponentMark))) {
                    ++tempOpponentStreak;
                    opponentMaxStreak = Math.max(opponentMaxStreak, tempOpponentStreak);
                } else {
                    tempOpponentStreak = 1;
                }
                // If the current cell and the next cell is marked by AI or a blank cell,
                // increment AI's streak.
                if ((cell.getContent().equals("") || cell.getContent().equals(this.aiMark))
                        && (nextCell.getContent().equals("") || nextCell.getContent().equals(this.aiMark))) {
                    ++tempAiStreak;
                    aiMaxStreak = Math.max(aiMaxStreak, tempAiStreak); // Calculate the max number of streak.
                } else {
                    tempAiStreak = 1;
                }
            }
            if (opponentMaxStreak >= this.numDiskToConnect) { // If there is a way to win for opponent in this diagonal
                ++opponentNumWaysToWin;
            }
            if (aiMaxStreak >= this.numDiskToConnect) { // If there is a way to win for AI in this diagonal
                ++aiNumWaysToWin;
            }
            // System.out
            // .println("Opponent ways to win: " + opponentNumWaysToWin + " | AI ways to
            // win: " + aiNumWaysToWin);
            // System.out.println("Max streak opponent: " + opponentMaxStreak + " | max
            // streak AI: " + aiMaxStreak);
            tempAiStreak = 1;
            tempOpponentStreak = 1;
            opponentMaxStreak = 0;
            aiMaxStreak = 0;
        }
        // System.out.println("Opponent ways to win: " + opponentNumWaysToWin + " | AI
        // ways to win: " + aiNumWaysToWin);

        if (aiMaxStreak == this.numDiskToConnect) {
            return Integer.MAX_VALUE;
        } else if (opponentMaxStreak == (this.numDiskToConnect - 1)) {
            return Integer.MIN_VALUE;
        } else {
            return aiNumWaysToWin - opponentNumWaysToWin;
            // return (aiMaxStreak * opponentNumWaysToWin) - (opponentMaxStreak *
            // aiNumWaysToWin);
        }
    }

    public int evalMove(final Board board) {
        int action = -1, depth = 1;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        // System.out.println("Passed in board");
        // board.printBoard();

        for (int selectedCol = 0; selectedCol < board.getBoardSize(); ++selectedCol) {
            Board boardState = new Board(board);
            // System.out.println("Board State copied ... ");
            // boardState.printBoard();
            int tempAlpha = this.maxValue(this.applyMove(boardState, selectedCol, aiMark), depth, alpha, beta);
            if (tempAlpha > alpha) {
                alpha = tempAlpha;
                action = selectedCol;
            }
        }

        // Might need to reset turn
        return action;
    }

    private int numDiskToConnect;
    private boolean aiTurn;
    private String opponentMark, aiMark;
    // private BoardChecker boardCheck;
    // private int alpha, beta, numDiskCon;
    // private Board boardState;

    public static void main(String args[]) {
        Board board = new Board(8);
        board.markMove(0, "x");
        board.markMove(1, "o");
        board.markMove(2, "o");
        board.markMove(3, "x");
        board.markMove(4, "o");
        board.markMove(5, "o");
        // board.markMove(6, "");
        // board.markMove(7, "");

        // Random rand = new Random();
        // int row = rand.nextInt(8);
        // int col = rand.nextInt(8);
        // for (int i = 0; i < row; ++i) {
        // for (int j = 0; j < col; ++j) {

        // if (rand.nextInt(100) % 2 == 0)
        // board.markMove(i, "x");
        // else
        // board.markMove(i, "o");
        // }
        // }
        board.printBoard();
        MoveEvaluator test = new MoveEvaluator(4, 1, "x", "o");

        System.out.println("Test for opponent: " + test.horizontal(board));
    }
}