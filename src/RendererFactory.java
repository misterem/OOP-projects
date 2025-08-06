/**
 * A factory class for creating a new renderer
 *
 * @author Moshe Altman
 */
public class RendererFactory {
	/**
	 * Constructor for a new renderer factory
	 */
	RendererFactory(){}

	/**
	 * Builds a new renderer of type "type"
	 * @param type of renderer
	 * @param size of board
	 * @return new Renderer object, or null if type invalid
	 */
	public Renderer buildRenderer(String type, int size){
		if (type.equals("console")) {
			return new ConsoleRenderer(size);
		} else if (type.equals("void")) {
			return new VoidRenderer();
		} else {
			return null;
		}
	}
}
