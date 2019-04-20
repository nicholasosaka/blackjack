import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DeckTest {
	private static Deck deck;

	@Before
	public void setUp(){
		deck = new Deck(true, 2);

	}

	@Test
	public void getDeck() {
		ArrayList<Card> deckReturned = deck.getDeck();
		ArrayList<Card> deckExpected = new ArrayList<>();
		deckExpected.add(new Card(Card.Suits.CLUBS, Card.Ranks.ACE));
		deckExpected.add(new Card(Card.Suits.CLUBS, Card.Ranks.TWO));

		Assert.assertEquals(deckExpected, deckReturned);
	}

	@Test
	public void addSingle() {
		deck.add(new Card(2));
		Assert.assertEquals(3, deck.getDeck().size());
	}

	@Test
	public void addMultiple() {
		ArrayList<Card> deckMultipleCards = new ArrayList<>();
		deckMultipleCards.add(new Card(Card.Suits.SPADES, Card.Ranks.KING));
		deckMultipleCards.add(new Card(Card.Suits.SPADES, Card.Ranks.ACE));

		deck.add(deckMultipleCards);
		Assert.assertEquals(4, deck.getDeck().size());

	}

	@Test
	public void dealSingle() {
		Card dealt = deck.deal();
		Card expectedCard = new Card(Card.Suits.CLUBS, Card.Ranks.ACE);

		Assert.assertEquals(expectedCard, dealt);

	}

	@Test
	public void dealMultiple() {

		ArrayList<Card> dealt = deck.deal(2);
		ArrayList<Card> deckExpected = new ArrayList<>();
		deckExpected.add(new Card(Card.Suits.CLUBS, Card.Ranks.ACE));
		deckExpected.add(new Card(Card.Suits.CLUBS, Card.Ranks.TWO));

		Assert.assertEquals(deckExpected, dealt);

	}

	@Test
	public void populateDeck() {
		deck.populate(5);
		Assert.assertEquals(7, deck.getDeck().size());
	}
}