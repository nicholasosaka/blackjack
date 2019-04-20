import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardTest {
	private static Card a;
	private static Card b;
	private static Card ace;


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

	@Before
	public void setUp() {
		a = new Card(Card.Suits.DIAMONDS, Card.Ranks.FIVE);
		b = new Card(Card.Suits.HEARTS, Card.Ranks.JACK);
		ace = new Card(Card.Suits.SPADES, Card.Ranks.ACE);

	}

	@Test
	public void getValueAce() {
		Assert.assertEquals(11, ace.getValue());
	}

	@Test
	public void getValueFace() {
		Assert.assertEquals(10, b.getValue());
	}

	@Test
	public void getValueNumeralCard() {
		Assert.assertEquals(5, a.getValue());
	}

	@Test
	public void toStringTest() {
		Assert.assertEquals("A♠", ace.toString());
	}
}