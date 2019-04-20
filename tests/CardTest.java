import org.junit.Assert;
import org.junit.Test;

public class CardTest {
	private static Card a;
	private static Card b;


	@Test
	public void compareToLesser() {
		a = new Card(Card.Suits.SPADES, Card.Ranks.TWO);
		b = new Card(Card.Suits.SPADES, Card.Ranks.JACK);

		Assert.assertEquals(-9,a.compareTo(b));
	}

	@Test
	public void compareToEqual() {
		a = new Card(Card.Suits.SPADES, Card.Ranks.FIVE);
		b = new Card(Card.Suits.SPADES, Card.Ranks.FIVE);

		Assert.assertEquals(0,a.compareTo(b));
	}

	@Test
	public void compareToGreater() {
		a = new Card(Card.Suits.SPADES, Card.Ranks.QUEEN);
		b = new Card(Card.Suits.SPADES, Card.Ranks.ACE);

		Assert.assertEquals(11,a.compareTo(b));
	}

}