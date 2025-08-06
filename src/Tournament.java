/**
 * Manages a tournament between two players over multiple rounds.
 * Alternates which player is X and O each round, runs each game, and tracks the outcome.
 * Displays the final results at the end of the tournament.
 *
 * @author Moshe Altman
 */
public class Tournament {
	private final int rounds;
	private final Renderer renderer;
	private final Player player1;
	private final Player player2;

	/**
	 * Constructs a new Tournament with the given players, renderer, and number of rounds.
	 *
	 * @param rounds the number of rounds in the tournament
	 * @param renderer the renderer used to display the board
	 * @param player1 the first player
	 * @param player2 the second player
	 */
	public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
		this.rounds = rounds;
		this.renderer = renderer;
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Runs the tournament, alternating starting players, and prints results.
	 *
	 * @param size the size of the board
	 * @param winStreak number of consecutive marks needed to win
	 * @param playerName1 name of player 1 (used in output)
	 * @param playerName2 name of player 2 (used in output)
	 */
	public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
		int player1Wins = 0;
		int player2Wins = 0;
		int ties = 0;

		for (int round = 0; round < rounds; round++) {
			// alternates between player1 and player2 being X
			Player xPlayer = (round % 2 == 0) ? player1 : player2;
			Player oPlayer = (round % 2 == 0) ? player2 : player1;

			Game game = new Game(xPlayer, oPlayer, size, winStreak, renderer);
			Mark winner = game.run();

			if(winner == Mark.BLANK){
				ties++;
			} else if (winner == Mark.X) {
				if (xPlayer == player1) {
					player1Wins++;
				} else {
					player2Wins++;
				}
			} else if (winner == Mark.O) {
				if (oPlayer == player1) {
					player1Wins++;
				} else {
					player2Wins++;
				}
			}
		}
		System.out.println("######### Results #########");
		System.out.println("Player 1, " + playerName1 + " won: " + player1Wins + " rounds");
		System.out.println("Player 2, " + playerName2 + " won: " + player2Wins + " rounds");
		System.out.println("Ties: " + ties);
	}

	/**
	 * Parses command-line arguments and starts the tournament.
	 *
	 * @param args command line arguments:
	 *             [0] rounds
	 *             [1] board size
	 *             [2] win streak
	 *             [3] render target
	 *             [4] player 1 type
	 *             [5] player 2 type
	 */
	public static void main(String[] args) {
		RendererFactory rendererFactory = new RendererFactory();
		PlayerFactory playerFactory = new PlayerFactory();

		// parse command-line arguments
		int rounds = Integer.parseInt(args[0]);
		int size = Integer.parseInt(args[1]);
		int winStreak = Integer.parseInt(args[2]);
		String target = args[3];
		String playerType1 = args[4];
		String playerType2 = args[5];

		Renderer renderer = rendererFactory.buildRenderer(target, size);
		Player player1 = playerFactory.buildPlayer(playerType1);
		Player player2 = playerFactory.buildPlayer(playerType2);

		Tournament tournament = new Tournament(rounds, renderer, player1, player2);
		tournament.playTournament(size, winStreak, playerType1, playerType2);
	}
}
