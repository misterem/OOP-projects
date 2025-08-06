/**
 * Represents a human player in the game.
 *
 * @author Moshe Altman
 */
public class HumanPlayer implements Player {
	private static final int BASE_TEN = 10;

	/**
	 * Constructs a new human player.
	 */
	public HumanPlayer() {}

	/**
	 * Prompts the human player to enter coordinates, validates the input,
	 * and attempts to place the given mark on the board.
	 *
	 * @param board the game board
	 * @param mark the mark to place
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		System.out.print("Player " + mark.toString() + " type coordinates: ");
		int input = KeyboardInput.readInt();
		int row = input / BASE_TEN;
		int col = input % BASE_TEN;

		boolean placed = board.putMark(mark, row, col);

		while(!placed) {
			if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
				System.out.print("Invalid mark position. Please choose a valid position: ");
			} else {
				System.out.print("Mark position is already occupied. Please choose a valid position: ");
			}
			input = KeyboardInput.readInt();
			row = input / BASE_TEN;
			col = input % BASE_TEN;
			placed = board.putMark(mark, row, col);
		}
	}
}
