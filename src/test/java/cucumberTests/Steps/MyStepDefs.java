package cucumberTests.Steps;

import Calendar.Calendar;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MyStepDefs {

    List<Calendar> listOfCalendars = new ArrayList<>();

    @Before()
    public void beforeEach(){
    assertEquals(0, listOfCalendars.size());
    }

    @When("merging calendars")
    public void mergingCalendars() {
        System.out.println("Stepity");
    }

    @Then("result is")
    public void resultIs() {
        System.out.println("Stepity");
    }

    @Then("possible events are")
    public void possibleEventsAre() {
        System.out.println("Stepity");
    }

    @Then("free time is")
    public void freeTimeIs() {
        System.out.println("Stepity");
    }


    @Then("toString returns {}")
    public void tostringReturns(String result) {
        for (Calendar cal : listOfCalendars) {
            assertEquals(result, cal.toString());
        }
    }

    @Given("calendars")
    public void calendars(List<Entry> table) {
        for (Entry l : table) {
            listOfCalendars.add(new Calendar(l.calendar,l.bounds));
        }
    }

    @Given("calendar {} with {}")
    public void calendarsCalendarWithBounds(String calendar, String bounds) {
        listOfCalendars.add(new Calendar(calendar,bounds));
    }

    static class Entry {
        public String calendar;
        public String bounds;
    }
}
