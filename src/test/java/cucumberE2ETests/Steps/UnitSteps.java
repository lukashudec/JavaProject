package cucumberE2ETests.Steps;

import calendar.legacy.CalendarClass;
import utilityClasses.utility.Entry;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitSteps {

    List<CalendarClass> listOfCalendars = new ArrayList<>();
    CalendarClass result;

    @When("merging calendars")
    public void mergingCalendars() {
        result = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1));
    }

    @Then("result is")
    public void resultIs(DataTable table) {
        Entry entry = new Entry(table, true);
        CalendarClass expected_result = new CalendarClass(entry.getRow(0).get(0), entry.getRow(0).get(1));
        assertEquals(expected_result, result);
    }

    @Then("possible events are {}")
    public void possibleEventsAre(String events) {
        String result_pretty = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1)).getPrettyTime();
        assertEquals(events, result_pretty);
    }

    @Then("free time is {}")
    public void freeTimeIs(String freeTime) {
        assertEquals(freeTime, listOfCalendars.get(0).getPrettyTime());
    }


    @Then("toString returns {}")
    public void toStringReturns(String result) {
        for (CalendarClass cal : listOfCalendars) {
            assertEquals(result, cal.toString());
        }
    }

    @Given("calendars")
    public void calendars(DataTable table) {
        Entry entry = new Entry(table, true);
        for (List<String> l : entry.data) {
            listOfCalendars.add(new CalendarClass(l.get(0), l.get(1)));
        }
    }

    @Given("calendar {} with {}")
    public void calendarsCalendarWithBounds(String calendar, String bounds) {
        listOfCalendars.add(new CalendarClass(calendar, bounds));
    }

    @Then("different creations are equal")
    public void differentCreationsAreEqual() {
        List<Integer[]> result_time = listOfCalendars.get(0).mergeWithCalendar(listOfCalendars.get(1)).getFreeTime();
        List<Integer[]> result_time2 = listOfCalendars.get(0).getPossibleEvents(listOfCalendars.get(1), 30);
        assertArrayEquals(result_time.toArray(), result_time2.toArray());

    }

    @Then("getCalendar is {}")
    public void getCalendar(String s) {
        assertEquals(s, listOfCalendars.get(0).getCalendar());
    }

}
