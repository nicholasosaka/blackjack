import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class DeckTest {
	private static Deck deck;

	@BeforeClass
	public static void setUp(){
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
		Assert.fail("Not yet implemented");
	}

	@Test
	public void addMultiple() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void dealSingle() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void dealMultiple() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void populateDeck() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void shuffleDeck() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void toStringDeck() {
		Assert.fail("Not yet implemented");
	}
}