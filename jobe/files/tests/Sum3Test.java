import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.*;

public class Sum3Test {

	@Test
	public void sum1() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

    Assert.assertEquals("sum3({1, 2, 3}) -> 6", 6, tester.sum3(new int[] {1, 2, 3}));
	}

	@Test
	public void sum2() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("sum3({5, 11, 2}) -> 18", 18, tester.sum3(new int[] {5, 11, 2}));
	}

	@Test
	public void sum3() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("sum3({7, 0, 0}) -> 7", 7, tester.sum3(new int[] {7, 0, 0}));
	}
}
