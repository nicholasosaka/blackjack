public class Card {
	//enums
	private enum Ranks {ACE, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
	private enum Suits {CLUBS, DIAMONDS, HEARTS, SPADES}

	private Ranks rank;
	private Suits suit;

	//primitives
	private boolean faceUp; //whether the card value is "visible"

	/**
	 * Construct a Card object via providing specific values for suit and rank
	 * @param suit suit value from Suits enum
	 * @param rank rank value from Ranks enum
	 */
	Card(Suits suit, Ranks rank){
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Construct a Card object via providing an index, which will create a Card in ascending order from index = 0 to 51
	 * @param index index of the card in a sorted 52 card deck.
	 */
	Card(int index){
		this.rank = Ranks.values()[index % 13];
		this.suit = Suits.values()[index / 13];
	}

	public boolean isFaceUp(){
		return faceUp;
	}

	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}


}
