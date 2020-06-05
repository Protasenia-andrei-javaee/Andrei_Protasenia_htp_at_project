package runners.cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.webservice"},
        features = {"src\\test\\java\\resources\\features\\findingUsers.feature"
        },
        snippets = SnippetType.CAMELCASE
)

public class Cucumber7 {

}
