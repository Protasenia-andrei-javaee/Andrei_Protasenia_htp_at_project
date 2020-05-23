package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.Booking2;
import tests.Booking3;
import tests.Booking1;


@RunWith(Suite.class)
@Suite.SuiteClasses({Booking1.class, Booking2.class, Booking3.class})
public class BookingRunner {
}
