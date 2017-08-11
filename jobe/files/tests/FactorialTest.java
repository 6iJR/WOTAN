import static org.junit.Assert.assertEquals;
import org.junit.*;

public class FactorialTest {
    @Test
	public void testA() {
	Prog tester = new Prog();

	//hint "Basisfall: Fakultaet mit 1";

	Assert.assertEquals("factorial(1) -> 1", 1, tester.factorial(1));
    }

    @Test
	public void testB() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 2";

	Assert.assertEquals("factorial(2) -> 2", 2, tester.factorial(2));
    }

    @Test
	public void testC() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 3";

	Assert.assertEquals("factorial(3) -> 6", 6, tester.factorial(3));
    }

    @Test
	public void testD() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 4";

	Assert.assertEquals("factorial(4) -> 24", 24, tester.factorial(4));
    }

    @Test
	public void testE() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 5";

	Assert.assertEquals("factorial(5) -> 120", 120, tester.factorial(5));
    }

    @Test
	public void testF() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 6";

	Assert.assertEquals("factorial(6) -> 720", 720, tester.factorial(6));
    }

    @Test
	public void testG() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 7";

	Assert.assertEquals("factorial(7) -> 5040", 5040, tester.factorial(7));
    }

    @Test
	public void testH() {
	Prog tester = new Prog();

	//hint "Rekursivfall: Fakultaet mit 8";

	Assert.assertEquals("factorial(8) -> 40320", 40320, tester.factorial(8));
    }
}