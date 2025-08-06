/**
 * An interface that represents a player
 */
public interface Player {
	/**
	 * Plays a single turn on board with mark
	 * @param board the board to play onto
	 * @param mark the mark to be played
	 */
	void playTurn(Board board, Mark mark);
}
