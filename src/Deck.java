import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<>();

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void add(Card card){
		deck.add(card);
	}

	public void add(ArrayList<Card> cards){
		deck.addAll(cards);
	}

	public Card deal(){
		Card c = deck.get(0);
		deck.remove(c);
		deck.subList(0,0).clear();
		return c;
	}

	public void shuffle(){
		Random rand = new Random(System.currentTimeMillis());
		int a,b;
		int shuffleLimit = deck.size() * deck.size();

		for(int i = 0; i < shuffleLimit; i++){
			a = rand.nextInt(deck.size());
			b = rand.nextInt(deck.size());

			swap(a,b);
		}
	}

	public void swap(int a, int b){
		Card temp = deck.get(a);
		deck.set(a,deck.get(b));
		deck.set(b, temp);
	}


	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(Card c : deck){
			str.append(c.toString()).append("  ");
		}
		return str.toString();
	}
}
