import java.util.InputMismatchException;
import java.util.Scanner;

public class Gameplay {

	public Gameplay(int boardSize, int numDiskToConnect, String myMark, String aiMark, int turn) {
		this.board = new Board(boardSize);
		this.scan = new Scanner(System.in);
		this.win = false;
		this.tie = false;
		this.myTurn = (turn == 1) ? true : false;
		this.myMark = myMark;
		this.aiMark = aiMark;
		this.moveEval = new MoveEvaluator(numDiskToConnect, turn, myMark, aiMark);
		this.check = new BoardChecker(boardSize, numDiskToConnect, myMark, aiMark);
	}

	public void play() {
		while (!win) {
			if (this.myTurn) {
				this.board.printBoard();
				// this.board.markMove(this.myMove(), this.myMark);
				this.myMove();
				this.myTurn = false;
				check.checkWin(this.board);
				// check for tie
			} else {
				this.board.printBoard();
				System.out.println("Computer move");
				this.computerMove();
				this.check.checkWin(this.board);
				// this.moveEval.vertical(this.board);

				this.myTurn = true;
				// check for winner
				// check for tie
			}
		}
	}

	public void myMove() {
		boolean validMove = false;
		int move = 0;
		System.out.print("Select a column number to drop: ");
		do {
			// Error check: input mismatch and out-of-bound check
			try {
				move = this.scan.nextInt();
				validMove = true;
				if (move >= this.board.getBoardSize())
					throw new InputMismatchException();
				if (!this.board.markMove(move, this.myMark))
					throw new Exception();
			} catch (InputMismatchException e) {
				System.out.print("Please enter an integer between 0 and " + (this.board.getBoardSize() - 1) + ": ");
				validMove = false;
			} catch (Exception e) {
				System.out.print("Invalid move! Please make another move: ");
				validMove = false;
			}
			this.scan.nextLine(); // Consume the buffer
		} while (!validMove);
	}

	public void computerMove() {
		// int move = 0;
		// while (!this.board.markMove(move, this.aiMark)) {
		// ++move;
		// }
		int move = this.moveEval.evalMove(this.board);
		// System.out.println("Return value from vertical: " + move);

		// Compute AI move here and error checking
		if (this.board.markMove(move, this.aiMark))
			return;
	}

	private Board board;
	private Scanner scan;
	private boolean win, tie, lost, myTurn;
	private String myMark, aiMark;
	private MoveEvaluator moveEval;
	private BoardChecker check;
}