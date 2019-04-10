import java.util.ArrayList;

public class Hand {

	ArrayList<Card> cards = new ArrayList<>();

	private boolean bust;
	int numberOfAces;

	Hand(){
		bust = false;
		numberOfAces = 0;
	}

	Hand(ArrayList<Card> cards){
		this.cards = cards;
		numberOfAces = 0;
	}

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


	public void hit(Card c){
		cards.add(c);
	}


	@Override
	public String toString(){
		String str = "";
		for(Card c : cards){
			str += c.toString() + "  ";
		}
		return str;
	}
}
