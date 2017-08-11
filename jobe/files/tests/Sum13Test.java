import static org.junit.Assert.assertEquals;
import org.junit.*;

public class Sum13Test {
    @Test
	public void testA() {
	Prog tester = new Prog();

	//hint "Leeres Array";

	Assert.assertEquals("sum13([]) -> 0", 0, tester.sum13(new int[] {}));
    }

    @Test
	public void testB() {
	Prog tester = new Prog();

	//hint "Eine 0";

	Assert.assertEquals("sum13([0]) -> 0", 0, tester.sum13(new int[] {0}));
    }

    @Test
	public void testC() {
	Prog tester = new Prog();

	//hint "Eine 13";

	Assert.assertEquals("sum13([13]) -> 0", 0, tester.sum13(new int[] {13}));
    }

    @Test
	public void testD() {
	Prog tester = new Prog();

	//hint "Zwei 13";

	Assert.assertEquals("sum13([13, 13]) -> 0", 0, tester.sum13(new int[] {13, 13}));
    }

    @Test
	public void testE() {
	Prog tester = new Prog();

	//hint "1 und 0 summiert";

	Assert.assertEquals("sum13([1, 0]) -> 1", 1, tester.sum13(new int[] {1, 0}));
    }

    @Test
	public void testF() {
	Prog tester = new Prog();

	//hint "2 und 7 summiert";

	Assert.assertEquals("sum13([2, 7]) -> 9", 9, tester.sum13(new int[] {2, 7}));
    }

    @Test
	public void testG() {
	Prog tester = new Prog();

	//hint "14 und 17 summiert";

	Assert.assertEquals("sum13([14, 17]) -> 31", 31, tester.sum13(new int[] {14, 17}));
    }

    @Test
	public void testH() {
	Prog tester = new Prog();

	//hint "Mehrere Zahlen";

	Assert.assertEquals("sum13([1, 4, 2, 9, 5]) -> 21", 21, tester.sum13(new int[] {1, 4, 2, 9, 5}));
    }

    @Test
	public void testI() {
	Prog tester = new Prog();

	//hint "Mehrere Zahlen die auch groesser 13 sind";

	Assert.assertEquals("sum13([7, 14, 17, 2, 4]) -> 44", 44, tester.sum13(new int[] {7, 14, 17, 2, 4}));
    }

    @Test
	public void testJ() {
	Prog tester = new Prog();

	//hint "Die 13 zaehlt nicht";

	Assert.assertEquals("sum13([2, 13]) -> 2", 2, tester.sum13(new int[] {2, 13}));
    }

    @Test
	public void testK() {
	Prog tester = new Prog();

	//hint "Die 8 zaehlt wegen der 13 nicht";

	Assert.assertEquals("sum13([1, 13, 8, 5]) -> 6", 6, tester.sum13(new int[] {1, 13, 8, 5}));
    }

    @Test
	public void testL() {
	Prog tester = new Prog();

	//hint "Die 1 zaehlt wegen der 13 nicht";

	Assert.assertEquals("sum13([13, 1, 13]) -> 0", 0, tester.sum13(new int[] {13, 1, 13}));
    }
}