import static org.junit.Assert.assertEquals;
import org.junit.*;

public class IntMaxTest {

	@Test
	public void testA() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(1, 2, 3) -> 3", 3, tester.intMax(1, 2, 3));
	}

	@Test
	public void testB() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(1, 3, 2) -> 3", 3, tester.intMax(1, 3, 2));
	}

	@Test
	public void testC() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(3, 2, 1) -> 3", 3, tester.intMax(3, 2, 1));
	}

	@Test
	public void testD() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(9, 3, 3) -> 9", 9, tester.intMax(9, 3, 3));
	}

	@Test
	public void testE() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(3, 9, 3) -> 9", 9, tester.intMax(3, 9, 3));
	}

	@Test
	public void testF() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(3, 3, 9) -> 9", 9, tester.intMax(3, 3, 9));
	}

	@Test
	public void testG() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(8, 2, 3) -> 8", 8, tester.intMax(8, 2, 3));
	}

	@Test
	public void testH() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(-3, -1, -2) -> -1", -1, tester.intMax(-3, -1, -2));
	}

	@Test
	public void testI() {
	Prog tester = new Prog();

	//hidden 0;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(6, 2, 5) -> 6", 6, tester.intMax(6, 2, 5));
	}

	@Test
	public void testJ() {
	Prog tester = new Prog();

	//hidden 1;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(5, 6, 2) -> 6", 6, tester.intMax(5, 6, 2));
	}

	@Test
	public void testK() {
	Prog tester = new Prog();

	//hidden 1;
	//hint "Ein Hint";

	Assert.assertEquals("intMax(5, 2, 6) -> 6", 6, tester.intMax(5, 2, 6));
	}
}
