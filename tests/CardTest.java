import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class CardTest {
	Random rand = new Random(System.currentTimeMillis());
	private Card a;
	private Card b;
	private int aIndex;
	private int bIndex;

	@Before
	public void setUp() throws Exception {
		aIndex = rand.nextInt(53);
		bIndex = rand.nextInt(53);
		Card a = new Card(aIndex);
		Card b = new Card(bIndex);

		Card.resetSuitIndex();
	}

	@Test
	public void getValue() {
		Assert.fail("Not yet implemented");
	}

	@Test
	public void compareTo() {
		Assert.fail("Not yet implemented");
	}

	@After
	public void tearDown() throws Exception {
		Card.resetSuitIndex();
	}
}