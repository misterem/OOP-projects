import java.util.Random;

/**
 * Represents a clever player in the game.
 *
 * @author Moshe Altman
 */
public class CleverPlayer implements Player {
	private final Random RANDOM = new Random();

	/**
	 * Constructs a new clever player.
	 */
	public CleverPlayer() {}

	/**
	 * "Flips a coin" to choose between placing mark randomly and placing
	 * mark at the place that creates the best line
	 *
	 * @param board the game board
	 * @param mark the mark to place
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int size = board.getSize();

		if (RANDOM.nextBoolean()){
			// place randomly
			int randRow = (int)(Math.random() * board.getSize());
			int randCol = (int)(Math.random() * board.getSize());
			boolean placed = board.putMark(mark, randRow, randCol);

			while(!placed) {
				randRow = (int)(Math.random() * board.getSize());
				randCol = (int)(Math.random() * board.getSize());
				placed = board.putMark(mark, randRow, randCol);
			}
		} else {
			// look for best line
			boolean placed = false;
			while(!placed) {
				int bestScore = -1;
				int bestRow = -1;
				int bestCol = -1;

				for (int row = 0; row < size; row++) {
					for (int col = 0; col < size; col++) {
						if (board.getMark(row, col) == Mark.BLANK) {
							int score = getLineLength(board, row, col, mark);

							if (score > bestScore) {
								bestScore = score;
								bestRow = row;
								bestCol = col;
							}
						}
					}
				}
				placed = board.putMark(mark, bestRow, bestCol);
			}
		}
	}

	/**
	 * Gets the line length if mark is placed at (row, col) on board
	 */
	private int getLineLength(Board board, int row, int col, Mark mark){
		int size = board.getSize();
		int[][] directions = {
				{0, 1},   // Horizontal
				{1, 0},   // Vertical
				{1, 1},   // Diagonal (top-left to bottom-right)
				{1, -1}   // Diagonal (top-right to bottom-left)
		};

		int maxLength = 1;

		for (int[] dir : directions) {
			int count = 1;

			// Check in positive direction
			int rowIteration = row + dir[0];
			int colIteration = col + dir[1];
			while (rowIteration >= 0 && rowIteration < size && colIteration >= 0 && colIteration < size &&
					board.getMark(rowIteration, colIteration) == mark) {
				count++;
				rowIteration += dir[0];
				colIteration += dir[1];
			}

			// Check in negative direction
			rowIteration = row - dir[0];
			colIteration = col - dir[1];
			while (rowIteration >= 0 && rowIteration < size && colIteration >= 0 && colIteration < size &&
					board.getMark(rowIteration, colIteration) == mark) {
				count++;
				rowIteration -= dir[0];
				colIteration -= dir[1];
			}

			maxLength = Math.max(maxLength, count);
		}

		return maxLength;
	}
}
