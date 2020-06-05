package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.booking1.ParisTest;
import tests.booking.booking2.MoskowTest;
import tests.booking.booking3.OsloTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ParisTest.class, MoskowTest.class, OsloTest.class})
public class BookingRunner {
}
