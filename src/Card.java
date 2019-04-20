/**
 * Playing Card Object.
 */
public class Card implements Comparable<Card>{
	//enums
	public enum Ranks {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
	public enum Suits {CLUBS, DIAMONDS, HEARTS, SPADES}

	private Ranks rank;
	private Suits suit;


	//primitives
	private boolean faceUp; //whether the card value is "visible"

	//statics
	private static int suitIndex = 0; //to hold which suit is to be used
	private static int cardNum = 0; //to hold the current card number, as generated.


	/**
	 * Construct a Card object via providing specific values for suit and rank.
	 * @param suit suit value from Suits enum.
	 * @param rank rank value from Ranks enum.
	 */
	Card(Suits suit, Ranks rank){
		this.rank = rank;
		this.suit = suit;
		this.faceUp = true;
	}


	/**
	 * Construct a Card object via providing an index, which will create a Card in ascending order from index = 0 to 51.
	 * @param index index of the card in a sorted 52 card deck.
	 */
	Card(int index){
		//rank value is dependent on index, cards can be arranged in equivalence classes on mod 13.
		this.rank = Ranks.values()[index % 13];
		this.suit = Suits.values()[suitIndex % 4];  //cycle through the 4 suits

		cardNum++;  //constructor generated another card
		if(cardNum % 13 == 0 & cardNum != 0){  //if this is the 13th card (mod 13) and not the first card
			suitIndex++;    //change suits
		}

		this.faceUp = true;
	}


	/**
	 * Reset suit index such that a fresh 52 deck can be created
	 */
	public static void resetSuitIndex(){
		cardNum = 0;
		suitIndex = 0;
	}


	/**
	 * Getter for boolean value, whether the card value is visible.
	 * @return whether the card is being "shown".
	 */
	public boolean isFaceUp(){
		return faceUp;
	}


	/**
	 * Setter for boolean value, whether the card value is visible.
	 * @param faceUp whether the card is being "shown"
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}


	/**
	 * Getter for the rank of the card.
	 * @return rank of card returned as an enumerated type of Ranks.
	 */
	public Ranks getRank(){
		return this.rank;
	}

	/**
	 * Method to check if card is an ace.
	 * @return true if card is an ace, otherwise false.
	 */
	public boolean isAce(){
		return this.getRank().equals(Card.Ranks.ACE);
	}

	/**
	 * Method to check if card is a face card (not an ace)
	 * @return  true if card is a face card, otherwise false
	 */
	public boolean isFace(){
		return (this.getRank().ordinal() > 9);
	}

	/**
	 * Method to get value of a card based on the rank value
	 * @return int 2 - 11
	 */
	public int getValue(){
		int value = 0;

		//if it's a face card, add 10
		//if its an ace, add 11 (maximum value of ace)
		//if it's neither, add the value on the card
		if(this.isFace()){
			value += 10;
		} else if (this.isAce()){
			value += 11;
		} else {
			value += this.getRank().ordinal() + 1;
		}

		return value;
	}

	/**
	 * Compares two cards.
	 * @param c Card to compare current card against.
	 * @return positive int if current card is worth more. negative int if current card is worth less. 0 if equal.
	 */
	@Override
	public int compareTo(Card c){
		return this.rank.compareTo(c.getRank());    //use enum compareTo
	}


	/**
	 * Places Card into format of two characters. If face down, return ##.
	 * @return String of length 2.
	 */
	@Override
	public String toString(){
		String[] suitUnicode = {"♣", "♦", "♥", "♠"};    //create a parallel array to Suits with the unicode equivalent to given suit
		String firstLetter;

		if(this.faceUp) {
			if (0 < this.rank.ordinal() && this.rank.ordinal() < 10) {   //if the card is a numerical one
				firstLetter = Integer.toString(this.rank.ordinal() + 1);    //firstLetter should be the ordinal value + 1
			} else {
				firstLetter = this.rank.name().substring(0, 1);  //if the card is Ace, Jack, Queen, or King, then set firstLetter to A, J, Q, K
			}
			return firstLetter + suitUnicode[suit.ordinal()];   //append the corresponding unicode token to after the letter
		} else {
			return "##";
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		if (isFaceUp() != card.isFaceUp()) return false;
		if (getRank() != card.getRank()) return false;
		return suit == card.suit;

	}
}
