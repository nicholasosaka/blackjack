import java.util.ArrayList;

/**
 * Hand object to hold deck and run calculations on hand value.
 */
public class Hand {

	private ArrayList<Card> cards = new ArrayList<>();

	private boolean bust;

	/**
	 * Default constructor for hand.
	 * Sets bust state to false and number of aces to 0.
	 */
	Hand(){
		bust = false;
	}

	/**
	 * Calculates value of hand, optimizes for Aces (value 1 or 11)
	 * @return int representative of the cumulative value of cards in the hand.
	 */
	public int getValue(){
		int value = 0;
		int numberOfAces = 0;
		boolean hasAce = false; //assume there are no aces

		for(Card c : cards){    //loop through each card in hand
			value += c.getRank().ordinal() + 1;

			if(c.getRank().equals(Card.Ranks.ACE)){ //if it's an ace
				value += 10;    //add 10. 1 was already added because 0 is an ace, and 0+1 is 1. adding 10 to the running total will be equivalent to having an ace worth 11
				numberOfAces++; //add an ace to the number of aces in the hand.
				hasAce = true;  //the hand does have an ace
			}

		}


		if(value > 21 && hasAce){   //if the value is a bust and there is at least one ace
			for(int i = 0; i < numberOfAces; i++){  //loop for the number of aces
				if(value - (i * 10) < 21){  //if making the ace equal to 1 will reduce hand value under 21, then return the value.
					return value - (i * 10);
				}
			}
		}

		return value;
	}


	/**
	 * Used to add a card into the hand.
	 * @param card Card to add into hand.
	 */
	public void hit(Card card){
		cards.add(card);
	}

	public void hit(ArrayList<Card> cards){
		this.cards.addAll(cards);
	}


	/**
	 * Used to return hand back into the deck.
	 * @return ArrayList of Cards.
	 */
	public ArrayList<Card> dump(){
		return cards;
	}


	/**
	 * toString override from Object.
	 * @return String formatted with each card separated by two spaces.
	 */
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();

		for(Card c : cards){    //for each card,
			str.append(c.toString()).append("  ");  //add the card representation and then add a space.
		}
		return str.toString();
	}
}
