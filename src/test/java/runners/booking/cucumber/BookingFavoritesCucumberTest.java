package runners.booking.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.booking.bookingFavorites"},
        features = {"src/test/resources/features/booking/BookingAddFavorites.feature"
        }
)
public class BookingFavoritesCucumberTest {
}