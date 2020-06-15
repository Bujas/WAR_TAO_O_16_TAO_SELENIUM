package cucumber.cucumberRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/cucumber/features/",
        plugin = {"pretty", "html:out"},
        glue = "cucumber/steps")


public class TestRunner {

}
