/**
 * Represents a square game board where each cell can hold a Mark value.
 * @author Moshe Altman
 */
public class Board {
	private final int SIZE;
	private final Mark[][] BOARD;

	/**
	 * Default constructor - initializes a 4x4 board.
	 */
	public Board(){
		this.SIZE = 4;
		this.BOARD = new Mark[SIZE][SIZE];
		// Fill each cell with the BLANK mark.
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				BOARD[i][j] = Mark.BLANK;
			}
		}
	}

	/**
	 * Constructor - initializes a size x size board.
	 * @param size The size to initialize the board
	 */
	public Board(int size){
		this.SIZE = size;
		this.BOARD = new Mark[size][size];
		// Fill each cell with the BLANK mark.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				BOARD[i][j] = Mark.BLANK;
			}
		}
	}

	/**
	 * Getter for size of board.
	 * @return size of board
	 */
	public int getSize(){
		return SIZE;
	}

	/**
	 * Attempts to place the given mark at the specified row and column.
	 * @param mark The mark to be placed
	 * @param row,col The row and column to place mark
	 * @return true if successful (i.e., within bounds and the cell is BLANK), false otherwise.
	 */
	public boolean putMark(Mark mark, int row, int col){
		if (row >= 0 && row < SIZE && col >= 0 && col < SIZE){ // in bounds
			if (getMark(row, col) == Mark.BLANK){ // space is empty
				BOARD[row][col] = mark;
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter for mark at (row, col)
	 * @param row,col The row and column to get mark
	 * @return The mark at the specified position, If the position is out of bounds, returns BLANK.
	 */
	public Mark getMark(int row, int col){
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) { // out of bounds
			return Mark.BLANK;
		} else{
			return BOARD[row][col];
		}
	}
}
