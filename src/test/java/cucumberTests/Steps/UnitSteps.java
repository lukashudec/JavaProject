package cucumberTests.Steps;

import Calendar.Calendar;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UnitSteps {

    List<Calendar> listOfCalendars = new ArrayList<>();
    Calendar result;

    @Before()
    public void beforeEach(){
    assertEquals(0, listOfCalendars.size());
    }

    @When("merging calendars")
    public void mergingCalendars() {
        result = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1));
    }

    @Then("result is")
    public void resultIs(List<Entry> table) {
        Calendar expected_result = new Calendar(table.get(0).calendar, table.get(0).bounds);
        assertEquals(expected_result, result);
    }

    @Then("possible events are {}")
    public void possibleEventsAre(String events) {
        String result_pretty = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1)).getPrettyTime();
        assertEquals(events, result_pretty);
    }

    @Then("free time is {}")
    public void freeTimeIs(String freeTime) {
        assertEquals(freeTime,listOfCalendars.get(0).getPrettyTime());
    }


    @Then("toString returns {}")
    public void toStringReturns(String result) {
        for (Calendar cal : listOfCalendars) {
            assertEquals(result, cal.toString());
        }
    }

    @Given("calendars")
    public void calendars(List<Entry> table) {
        for (Entry l : table) {
            listOfCalendars.add(l.getCalendar());
        }
    }

    @Given("calendar {} with {}")
    public void calendarsCalendarWithBounds(String calendar, String bounds) {
        listOfCalendars.add(new Calendar(calendar,bounds));
    }

    @Then("different creations are equal")
    public void differentCreationsAreEqual() {
        List<Integer[]> result_time = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1)).getFreeTime();
        List<Integer[]> result_time2 = listOfCalendars.get(0).getPossibleEvents(listOfCalendars.get(1), 30);
        assertArrayEquals(result_time.toArray(), result_time2.toArray());

    }

    @Then("getCalendar is {}")
    public void getCalendar(String s) {
        assertEquals(s,listOfCalendars.get(0).getCalendar());
    }

    static class Entry {
        public String calendar;
        public String bounds;

        public Calendar getCalendar() {
            return new Calendar(calendar, bounds);
        }
    }
}
