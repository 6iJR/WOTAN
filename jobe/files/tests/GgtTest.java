import static org.junit.Assert.assertEquals;
import org.junit.*;

public class GgtTest {
    @Test
	public void testA() {
	Prog tester = new Prog();

	//hint "Basisfall: GGT mit zwei negativen Zahlen";

	Assert.assertEquals("ggt(-16, -4) -> -1", -1, tester.ggt(-16, -4));
    }

    @Test
	public void testB() {
	Prog tester = new Prog();

	//hint "Basisfall: GGT mit einer negativen Zahl";

	Assert.assertEquals("ggt(-16, 4) -> -1", -1, tester.ggt(-16, 4));
    }

    @Test
	public void testC() {
	Prog tester = new Prog();

	//hint "Basisfall: GGT mit einer negativen Zahl";

	Assert.assertEquals("ggt(16, -4) -> 1", -1, tester.ggt(16, -4));
	}

    @Test
	public void testD() {
	Prog tester = new Prog();

	//hint "Basisfall: y ist 0";

	Assert.assertEquals("ggt(8, 0) -> 8", 8, tester.ggt(8, 0));
	}

    @Test
	public void testE() {
	Prog tester = new Prog();

	//hint "Basisfall: x ist 0";

	Assert.assertEquals("ggt(0, 19) -> 19", 19, tester.ggt(0, 19));
	}

    @Test
	public void testF() {
	Prog tester = new Prog();

	//hint "Rekursivfall mit Primzahlen";

	Assert.assertEquals("ggt(17, 7) -> 1", 1, tester.ggt(17, 7));
    }

    @Test
	public void testG() {
	Prog tester = new Prog();

	//hint "Rekursivfall mit Primzahlen";

	Assert.assertEquals("ggt(7, 17) -> 1", 1, tester.ggt(7, 17));
    }

    @Test
	public void testH() {
	Prog tester = new Prog();

	//hint "Rekursivfall";

	Assert.assertEquals("ggt(3, 9) -> 3", 3, tester.ggt(3, 9));
    }

    @Test
	public void testI() {
	Prog tester = new Prog();

	//hint "Rekursivfall";

	Assert.assertEquals("ggt(8, 6) -> 2", 2, tester.ggt(8, 6));
    }

    @Test
	public void testJ() {
	Prog tester = new Prog();

	//hint "Rekursivfall";

	Assert.assertEquals("ggt(18, 24) -> 6", 6, tester.ggt(18, 24));
    }

    @Test
	public void testK() {
	Prog tester = new Prog();

	//hint "Rekursivfall";

	Assert.assertEquals("ggt(3, 3) -> 3", 3, tester.ggt(3, 3));
	}
}