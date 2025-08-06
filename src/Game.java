/**
 * Manages the logic and flow of a game between two players.
 * Alternates turns, renders the board after each move, and checks for a winner.
 * The game ends when one player wins or when the board is full.
 *
 * @author Moshe Altman
 */
public class Game {
	private static final int DEFAULT_BOARD_SIZE = 4;
	private static final int DEFAULT_WIN_STREAK = 3;

	private final Player playerX;
	private final Player playerO;
	private final int size;
	private final int winStreak;
	private final Renderer renderer;

	/**
	 * Constructs a Game with default size and win streak.
	 *
	 * @param playerX the player using mark X
	 * @param playerO the player using mark O
	 * @param renderer the renderer to use for displaying the board
	 */
	public Game(Player playerX, Player playerO, Renderer renderer) {
		this.playerX = playerX;
		this.playerO = playerO;
		this.size = DEFAULT_BOARD_SIZE;
		this.winStreak = DEFAULT_WIN_STREAK;
		this.renderer = renderer;
	}

	/**
	 * Constructs a Game with a specified board size and win streak.
	 *
	 * @param playerX the player using mark X
	 * @param playerO the player using mark O
	 * @param size the size of the board (size x size)
	 * @param winStreak the number of consecutive marks needed to win
	 * @param renderer the renderer to use for displaying the board
	 */
	public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
		this.playerX = playerX;
		this.playerO = playerO;
		this.size = size;
		this.winStreak = winStreak;
		this.renderer = renderer;
	}

	/**
	 * Returns the win streak required to win the game.
	 * @return the number of consecutive marks required to win
	 */
	public int getWinStreak(){
		return winStreak;
	}

	/**
	 * Returns the size of the game board.
	 * @return the dimension of the board (size x size)
	 */
	public int getBoardSize(){
		return size;
	}

	/**
	 * Runs the game loop: players take turns, the board is updated and rendered,
	 * and the winner is determined if any.
	 *
	 * @return the mark of the winning player, or Mark.BLANK if it's a tie
	 */
	public Mark run(){
		int turnCount = 0;
		Board board = new Board(size);
		renderer.renderBoard(board);

		while(turnCount < (size * size)){
			playerX.playTurn(board, Mark.X);
			turnCount++;
			renderer.renderBoard(board);
			if (checkWin(board, Mark.X)){
				return Mark.X;
			}

			if (turnCount == (size * size)){
				return Mark.BLANK;
			}

			playerO.playTurn(board, Mark.O);
			turnCount++;
			renderer.renderBoard(board);
			if (checkWin(board, Mark.O)){
				return Mark.O;
			}
		}
		return Mark.BLANK;
	}

	/**
	 * Checks whether the given player has won the game.
	 */
	private boolean checkWin(Board board, Mark mark){
		// Horizontal win check
		for (int row = 0; row < size; row++) {
			int consecutive = 0;
			for (int col = 0; col < size; col++) {
				if (board.getMark(row, col) == mark) {
					consecutive++;
					if (consecutive == winStreak) {
						return true;
					}
				}
				else {
					consecutive = 0;
				}
			}
		}

		// Vertical win check
		for (int col = 0; col < size; col++) {
			int consecutive = 0;
			for (int row = 0; row < size; row++) {
				if (board.getMark(row, col) == mark) {
					consecutive++;
					if (consecutive == winStreak) {
						return true;
					}
				}
				else {
					consecutive = 0;
				}
			}
		}

		// Diagonal (\) win check
		for (int startRow = 0; startRow <= (size - winStreak); startRow++) {
			for (int startCol = 0; startCol <= (size - winStreak); startCol++) {
				int consecutive = 0;
				for (int offset = 0; offset <= Math.min(size - startRow, size - startCol); offset++) {
					if (board.getMark(startRow + offset, startCol + offset) == mark) {
						consecutive++;
						if (consecutive == winStreak) {
							return true;
						}
					}
					else {
						consecutive = 0;
					}
				}
			}
		}

		// Diagonal (/) win check
		for (int startRow = 0; startRow <= (size - winStreak); startRow++) {
			for (int startCol = winStreak - 1; startCol < size; startCol++) {
				int consecutive = 0;
				for (int offset = 0; offset <= Math.min(size - startRow, startCol + 1); offset++) {
					if (board.getMark(startRow + offset, startCol - offset) == mark) {
						consecutive++;
						if (consecutive == winStreak) {
							return true;
						}
					}
					else {
						consecutive = 0;
					}
				}
			}
		}
		return false;
	}
}
