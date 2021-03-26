public class Board {

	public Board(int size) {
		this.boardSize = size;
		this.movesMadeByCol = new int[size];
		for (int i = 0; i < size; ++i) // Initialize to size - 1 to start from the bottom of the 2d array
			this.movesMadeByCol[i] = size - 1;
		this.createBoard();

	}

	public Board(final Board board) {
		this.boardSize = board.boardSize;
		this.createBoard();
		this.movesMadeByCol = new int[this.boardSize];
		for (int i = 0; i < this.boardSize; ++i) {
			this.movesMadeByCol[i] = board.movesMadeByCol[i];
		}
		for (int i = 0; i < this.boardSize; ++i) {
			for (int j = 0; j < this.boardSize; ++j) {
				this.cells[i][j].setContent(board.cells[i][j].getContent());
			}
		}
	}

	private void createBoard() {
		this.cells = new Cell[this.boardSize][this.boardSize];
		for (int i = 0; i < this.boardSize; ++i) { // Initialize the board
			for (int j = 0; j < this.boardSize; ++j) {
				this.cells[i][j] = new Cell();
				this.cells[i][j].setContent(""); // Debug
			}
		}
	}

	/**
	 * Mark the move using the column number
	 * 
	 * @param moveCol
	 */
	public boolean markMove(int moveCol, String mark) {
		if (!this.validMove(moveCol))
			return false;
		int row = movesMadeByCol[moveCol]--; // Get the correct row and decrement it for the next pick
		this.cells[row][moveCol].pick(mark); // Pick the cell
		return true;
	}

	public void printBoard() {
		String begin = "  +";
		String sub = "---+";

		System.out.print("    ");
		for (int i = 0; i < this.boardSize; ++i) // Print column number
		{
			if (i < (this.boardSize - 1))
				System.out.print(i + "   ");
			else
				System.out.print(i);
		}

		System.out.print("\n" + begin);
		for (int i = 0; i < this.boardSize; ++i) // Print horizontal line
			System.out.print(sub);

		for (int i = 0; i < this.boardSize; ++i) // Print subsequent rows
		{
			System.out.print("\n  |");
			for (int j = 0; j < this.boardSize; ++j) // Print vertical bars
			{
				if (!this.cells[i][j].isPicked())
					System.out.print("   |");
				else
					System.out.print(" " + cells[i][j] + " |");

			}

			System.out.print("\n" + begin); // Print horizontal line
			for (int k = 0; k < this.boardSize; ++k)
				System.out.print(sub);
		}
		System.out.println();
	}

	public int getBoardSize() {
		return this.boardSize;
	}

	public Cell getCell(int i, int j) {
		return this.cells[i][j];
	}

	public int[] getMovesMadeByColumn() {
		return this.movesMadeByCol;
	}

	public boolean validMove(int moveCol) {
		return (this.movesMadeByCol[moveCol] >= 0);
	}

	private Cell[][] cells;
	private int boardSize;
	private int[] movesMadeByCol; // Holds the moves made in the 2d array

}
