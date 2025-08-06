/**
 * Renders a given Board to void (doesn't render)
 * @author Moshe Altman
 */
public class VoidRenderer implements Renderer {

	/**
	 * Initializes the renderer.
	 */
	public VoidRenderer() {}

	/**
	 * Does nothing
	 * @param board the board to not print.
	 */
	@Override
	public void renderBoard(Board board) {}
}
