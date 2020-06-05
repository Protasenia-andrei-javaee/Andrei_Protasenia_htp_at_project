package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.createNewUser"},
        features = {"src\\test\\java\\resources\\features\\bookingRegistration.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class Cucumbe4 {
}
