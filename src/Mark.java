public enum Mark {
	BLANK, X, O;

	/**
	 * Returns the mark as a String
	 * @return mark as String
	 */
	public String toString(){
		return switch (this) {
			case BLANK -> null;
			case X -> "X";
			case O -> "O";
		};
	}
}
