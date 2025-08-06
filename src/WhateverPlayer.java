/**
 * Represents a whatever player in the game.
 *
 * @author Moshe Altman
 */
public class WhateverPlayer implements Player {
	/**
	 * Constructs a new whatever player.
	 */
	public WhateverPlayer() {}

	/**
	 * Chooses a random row and column and places mark
	 *
	 * @param board the game board
	 * @param mark the mark to place
	 */
	@Override
	public void playTurn(Board board, Mark mark) {
		int randRow = (int)(Math.random() * board.getSize());
		int randCol = (int)(Math.random() * board.getSize());
		boolean placed = board.putMark(mark, randRow, randCol);

		while(!placed) {
			randRow = (int)(Math.random() * board.getSize());
			randCol = (int)(Math.random() * board.getSize());
			placed = board.putMark(mark, randRow, randCol);
		}
	}
}
