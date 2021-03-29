package cucumberE2ETests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/java/cucumberE2ETests/Features/UNIT",
        glue = "/cucumberE2ETests/Steps/UnitSteps")
class RunUnitTests {

    @Test
    void testJUnit5Conversion() {

    }

}
