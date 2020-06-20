package runners.booking.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking.bookingHeader"},
        features = {"src/test/resources/features/booking/BookingCheckHeader.feature"
        }
)
public class BookingHeaderCucumberTest {
}