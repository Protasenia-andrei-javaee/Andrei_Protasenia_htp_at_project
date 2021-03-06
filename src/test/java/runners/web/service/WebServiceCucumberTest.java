package runners.web.service;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber.web.service"},
        features = {"src/test/resources/features/web_service/WebService.feature"
        },
        snippets = SnippetType.CAMELCASE
)
public class WebServiceCucumberTest {
}