import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> cards = new ArrayList<>();

	private boolean bust;
	private int numberOfAces;

	Hand(){
		bust = false;
		numberOfAces = 0;
	}

	/**
	 * Calculates value of hand, optimizes for Aces (value 1 or 11)
	 * @return int representative of the cumulative value of cards in the hand.
	 */
	public int getValue(){
		int value = 0;
		boolean hasAce = false;

		for(Card c : cards){
			value += c.getRank().ordinal() + 1;

			if(c.getRank().equals(Card.Ranks.ACE)){
				value += 10;
				numberOfAces++;
				hasAce = true;
			}

		}


		if(value > 21 && hasAce){
			for(int i = 0; i < numberOfAces; i++){
				if(value - (i * 10) < 21){
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
		for(Card c : cards){
			str.append(c.toString()).append("  ");
		}
		return str.toString();
	}
}
