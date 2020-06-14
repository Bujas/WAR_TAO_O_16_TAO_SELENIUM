package cucumberrunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/cucumber/features/",
    glue = "steps",
   //tags = {"@Task6a"},
    plugin = {"pretty", "html:out"})
public class TestRunner {

}
