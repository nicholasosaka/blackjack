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
		boolean hasAce = false; //assume there are no aces

		for(Card c : cards){    //loop through each card in hand

			value += c.getValue();  //get value (2-11)

			if(c.isAce()){ //if it's an ace
				hasAce = true;  //the hand does have an ace
			}

		}

		if(value > 21 && hasAce){   //if the value is a bust and there is at least one ace
			for(int i = 0; i < findAces(); i++){  //loop for the number of aces
				if(value - (i * 10) < 21){  //if making the ace equal to 1 will reduce hand value under 21, then return the value.
					return value - (i * 10);
				}
			}
		}

		return value;
	}

	/**
	 * Find number of aces in the hand
	 * @return  int representing number of aces
	 */
	private int findAces(){
		int number = 0;

		for(Card c : cards){    //for each card
			if(c.getRank().equals(Card.Ranks.ACE)){ //if its an ace
				number++;   //increment number of aces by one.
			}
		}

		return number;
	}

	/**
	 * Method to determine if the player has a blackjack
	 * @return true if blackjack
	 */
	public boolean isBlackjack(){
		Card firstCard = cards.get(0);
		Card secondCard = cards.get(1);

		if(firstCard.isAce() || firstCard.isFace() || firstCard.getRank().ordinal() == 10){         //both cards have to be either ace, face or 10.
			if(secondCard.isAce() || secondCard.isFace() || secondCard.getRank().ordinal() == 10){

				return firstCard.getValue() + secondCard.getValue() == 21;  //and both cards have to add up to 21.
			}
		}
		return false;
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
