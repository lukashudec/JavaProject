package unitTests;

import calendar.CalendarCreator;
import calendar.legacy.CalendarClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalendarCreatorTest {
    @Test
    void testGetFreeTime() {
        CalendarCreator calendar = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
        assertEquals(calendar.getFreeTime().toString(),calendar.getFreeTime(30).toString());
    }

    @Test
    void testGetFreeTimeWithoutParam() {
        CalendarCreator calendar = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
        assertEquals("[540-720, 780-960, 1080-1200]",calendar.getFreeTime().toString());
    }

    @Test
    void testGetFreeTimeWithParam() {
        CalendarCreator calendar = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
        assertEquals("[540-720, 780-960, 1080-1200]",calendar.getFreeTime(30).toString());

        assertEquals("[540-720, 780-960, 1080-1200]",calendar.getFreeTime(120).toString());
        assertEquals("[540-720, 780-960]",calendar.getFreeTime(121).toString());

        assertEquals("[]",calendar.getFreeTime(500).toString());
        assertEquals("[540-720, 780-960, 1080-1200]",calendar.getFreeTime(0).toString());
    }

    @Test
    void testGetFreeTimeFromTwoCalendarsWithoutParam() {
        CalendarCreator calendarMerge1 = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
        CalendarCreator calendarMerge2 = new CalendarCreator("10:00-11:45,12:30-14:30", "10:00-18:30");
        assertEquals("[870-960, 1080-1110]",calendarMerge1.getFreeTime(calendarMerge2).toString());
    }

    @Test
    void testGetFreeTimeFromTwoCalendarsWithParam() {
        CalendarCreator calendarMerge1 = new CalendarCreator("12:00-13:00,16:00-18:00", "09:00-20:00");
        CalendarCreator calendarMerge2 = new CalendarCreator("10:00-11:45,12:30-14:30", "10:00-18:30");
        assertEquals("[870-960, 1080-1110]",calendarMerge1.getFreeTime(calendarMerge2,30).toString());
        assertEquals("[870-960]",calendarMerge1.getFreeTime(calendarMerge2,31).toString());
    }


    @Test
    void testMerging() {
        CalendarCreator calendarMerge1 = new CalendarCreator("09:00-10:00,14:30-18:00", "09:00-20:00");
        CalendarCreator calendarMerge2 = new CalendarCreator("16:00-19:30", "13:00-20:30");
        assertEquals("[0-780, 870-1170, 1200-1440]",calendarMerge1.mergeWith(calendarMerge2).getEvents().toString());
    }
}