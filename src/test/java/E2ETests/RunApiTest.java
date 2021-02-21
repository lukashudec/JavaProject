package E2ETests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features="src/test/java/E2ETests/Features/API",
        glue = "/E2ETests/Steps/ApiSteps")
public class RunApiTest {

}
