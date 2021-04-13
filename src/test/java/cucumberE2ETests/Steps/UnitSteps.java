package cucumberE2ETests.Steps;

import calendar.CalendarCreator;
import calendar.utility.Events;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilityClasses.utility.Entry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitSteps {

    List<CalendarCreator> listOfCalendars = new ArrayList<>();
    CalendarCreator result;

    @Given("calendars")
    public void calendars(DataTable table) {
        Entry entry = new Entry(table, true);
        for (List<String> l : entry.data) {
            listOfCalendars.add(new CalendarCreator(l.get(0), l.get(1)));
        }
    }

    @Given("calendar {} with {}")
    public void calendarsCalendarWithBounds(String calendar, String bounds) {
        listOfCalendars.add(new CalendarCreator(calendar, bounds));
    }

    @When("merging calendars")
    public void mergingCalendars() {
        result = listOfCalendars.get(0).mergeWith(listOfCalendars.get(1));
    }

    @Then("result is")
    public void resultIs(DataTable table) {
        Entry entry = new Entry(table, true);
        CalendarCreator expected_result = new CalendarCreator(entry.getRow(0).get(0), entry.getRow(0).get(1));
        assertEquals(expected_result.getEvents(), result.getEvents());
    }

    @Then("possible events are {}")
    public void possibleEventsAre(String events) {
        String result_pretty = listOfCalendars.get(0).mergeWith(listOfCalendars.get(1)).getFreeTime().toPrettyString();
        assertEquals(events, result_pretty);
    }

    @Then("free time is {}")
    public void freeTimeIs(String freeTime) {
        assertEquals(freeTime, listOfCalendars.get(0).getFreeTime().toPrettyString());
    }


    @Then("toString returns {}")
    public void toStringReturns(String result) {
        for (CalendarCreator cal : listOfCalendars) {
            assertEquals(result, cal.getEvents().toPrettyString());
        }
    }

    @Then("different creations are equal")
    public void differentCreationsAreEqual() {
        Events result_time = listOfCalendars.get(0).mergeWith(listOfCalendars.get(1)).getFreeTime();
        Events result_time2 = listOfCalendars.get(0).getFreeTime(listOfCalendars.get(1), 30);
        assertArrayEquals(result_time.toArray(), result_time2.toArray());

    }

    @Then("getEvents.toString is {}")
    public void getCalendar(String s) {
        assertEquals(s, listOfCalendars.get(0).getEvents().toString());
    }

}
