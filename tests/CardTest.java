import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class CardTest {
	private static Random rand = new Random(System.currentTimeMillis());
	private static Card a;
	private static Card b;

	@BeforeClass
	public static void setUp() {
		a = new Card(rand.nextInt(53));
		b = new Card(rand.nextInt(53));
	}

	@Test
	public void compareTo() {

		int aVal = a.getRank().ordinal() + 1;
		int bVal = b.getRank().ordinal() + 1;

		Assert.assertEquals(a.compareTo(b) > 0,aVal > bVal);
		Assert.assertEquals(a.compareTo(b) == 0, aVal == bVal);
		Assert.assertEquals(a.compareTo(b) < 0, aVal < bVal);

	}

	@AfterClass
	public static void tearDown() {
		Card.resetSuitIndex();
	}
}