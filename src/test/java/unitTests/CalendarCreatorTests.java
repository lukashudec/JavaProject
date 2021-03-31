package unitTests;

import calendar.legacy.CalendarClass;
import calendar.CalendarCreator;
import org.junit.jupiter.api.Test;

import java.util.List;


class CalendarCreatorTests {
    CalendarCreator calendar = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarClass oldCalendar = new CalendarClass("12:00-13:00,16:00-18:00", "09:00-20:00");

    CalendarCreator calendarMerge1 = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarCreator calendarMerge2 = new CalendarCreator("10:00-11:45,12:30-14:30", "10:00-18:30");

    CalendarClass oldCalendarMerge1 = new CalendarClass("12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarClass oldCalendarMerge2 = new CalendarClass("10:00-11:45,12:30-14:30", "10:00-18:30");


    @Test
    void testCalendars() {
    }
}

