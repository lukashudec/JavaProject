package cucumberE2ETests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/java/cucumberE2ETests/Features/WEB",
        glue = {"cucumberE2ETests/pages", "utilityClasses/utility"}
)
public class RunE2ETests {

}
