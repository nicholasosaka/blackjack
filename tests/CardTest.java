import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

public class CardTest {
	static Random rand = new Random(System.currentTimeMillis());
	private static Card a;
	private static Card b;
	private static int aIndex;
	private static int bIndex;

	@BeforeClass
	public static void setUp() throws Exception {
		aIndex = rand.nextInt(53);
		bIndex = rand.nextInt(53);
		a = new Card(aIndex);
		b = new Card(bIndex);

	}

	@Test
	public void compareTo() {

		System.out.println(a.toString() + " | " + b.toString());

		int aVal = a.getRank().ordinal() + 1;
		int bVal = b.getRank().ordinal() + 1;

		Assert.assertEquals(a.compareTo(b) > 0,aVal > bVal);
		Assert.assertEquals(a.compareTo(b) == 0, aVal == bVal);
		Assert.assertEquals(a.compareTo(b) < 0, aVal < bVal);

	}

	@AfterClass
	public static void tearDown() throws Exception {
		Card.resetSuitIndex();
	}
}