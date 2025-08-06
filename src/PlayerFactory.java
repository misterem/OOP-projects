/**
 * A factory class for creating a new player
 *
 * @author Moshe Altman
 */
public class PlayerFactory {
	/**
	 * Constructor for a new player factory
	 */
	PlayerFactory() {}

	/**
	 * Builds a new player of type "type"
	 * @param type of player
	 * @return new Player object, or null if type invalid
	 */
	public Player buildPlayer(String type){
		if(type.equals("human")){
			return new HumanPlayer();
		} else if(type.equals("whatever")){
			return new WhateverPlayer();
		} else if(type.equals("clever")){
			return new CleverPlayer();
		} else if(type.equals("genius")){
			return new GeniusPlayer();
		} else{
			return null;
		}
	}
}
