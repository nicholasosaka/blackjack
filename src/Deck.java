import java.util.ArrayList;
import java.util.Random;

/**
 * Deck of playing Card objects.
 */
public class Deck {
	private ArrayList<Card> deck = new ArrayList<>();

	/**
	 * Constructor for Deck. Populates deck with a number of cards
	 * @param freshDeck whether to reset suit/ranks to create a fresh deck
	 * @param numberOfCards number of cards to populate
	 */
	Deck(boolean freshDeck, int numberOfCards){
		if(freshDeck) {
			Card.resetSuitIndex();  //if freshDeck is true, then reset suit index of cards
		}
		populate(numberOfCards);
	}

	/**
	 * Getter for deck
	 * @return returns ArrayList of type Card
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

	/**
	 * Add a card to the deck
	 * @param card Card to add
	 */
	public void add(Card card){
		deck.add(card);
	}

	/**
	 * Add many cards to the deck
	 * @param cards cards to add
	 */
	public void add(ArrayList<Card> cards){
		deck.addAll(cards);
		this.shuffle(); //ensure it's shuffled after adding card
	}

	/**
	 * Deal a card
	 * @return card object to deal
	 */
	public Card deal(){
		Card c = deck.get(0);   //grab a card

		deck.remove(c);     //remove the card from the deck, then resize it.
		deck.subList(0,0).clear();
		return c;
	}

	/**
	 * Deal multiple cards
	 * @param numberOfCards number of cards to be dealt
	 * @return list of card objects dealt
	 */
	public ArrayList<Card> deal(int numberOfCards){
		ArrayList<Card> cardsToDeal = new ArrayList<>();

		for(int i = 0; i < numberOfCards; i++){ //repeat deal logic for number of cards to deal
			cardsToDeal.add(deck.get(0));
			deck.remove(deck.get(0));
			deck.subList(0,0).clear();
		}

		return cardsToDeal;
	}

	/**
	 * Populate deck with cards
	 * @param numberOfCards number of cards to populate deck with
	 */
	public void populate(int numberOfCards){
		for(int i = 0; i < numberOfCards; i++){
			deck.add(new Card(i));  //add a card n times
		}
	}

	/**
	 * Shuffle deck n^2 times for a deck of size n.
	 */
	public void shuffle(){
		Random rand = new Random(System.currentTimeMillis());
		int a,b;
		int shuffleLimit = deck.size() * deck.size();   //to almost guarantee shuffling of deck

		for(int i = 0; i < shuffleLimit; i++){  //swap random cards a, b.
			a = rand.nextInt(deck.size());
			b = rand.nextInt(deck.size());

			swap(a,b);
		}
	}

	private void swap(int a, int b){    //basic swap method, uses indexes to prevent any pass-by-reference issues
		Card temp = deck.get(a);
		deck.set(a,deck.get(b));
		deck.set(b, temp);
	}


	/**
	 * Method to stringify deck
	 * @return n*4 length string given n cards in deck
	 */
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(Card c : deck){
			str.append(c.toString()).append("  ");
		}
		return str.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Deck deckCompare = (Deck) o;

		if(this.getDeck().size() != deckCompare.getDeck().size()) return false;

		for(int i = 0; i < this.getDeck().size(); i++){
			if(!this.getDeck().get(i).equals(deckCompare.getDeck().get((i)))) return false;
		}

		return true;

	}

	@Override
	public int hashCode() {
		return getDeck().hashCode();
	}
}
