package cucumberUnitTests.Steps;

import Calendar.Calendar;
import cucumberUnitTests.utility.Entry;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

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
    public void resultIs(DataTable table) {
        Entry entry = new Entry(table,true);
        Calendar expected_result = new Calendar(entry.getRow(0).get(0), entry.getRow(0).get(1));
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
    public void calendars(DataTable table) {
        Entry entry = new Entry(table,true);
        for (List<String> l : entry.data) {
            listOfCalendars.add(new Calendar(l.get(0),l.get(1)));
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

}
