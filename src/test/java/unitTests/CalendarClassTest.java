package unitTests;

import calendar.CalendarClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CalendarClassTest {
    CalendarClass calendar1 = new CalendarClass(new ArrayList<>(List.of
            (new String[]{"09:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})), new String[]{"09:00", "20:00"});
    CalendarClass calendar2 = new CalendarClass("09:00-10:30,12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarClass calendar3 = new CalendarClass("12:00-13:00,16:00-18:00", "09:00-20:00");
    CalendarClass calendar4 = new CalendarClass("10:00-11:45,12:30-14:30", "10:00-18:30");

    @Test
    public void testToString() {
        String result = "[[09:00, 10:30], [12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00]";
        assertEquals(result, calendar1.toString(), "Not equal");
    }

    @Test
    public void testCalendarCreation() {
        assertEquals(calendar1.toString(), calendar2.toString(), "Not equal");
    }

    @Test()
    public void testCalendarCreationFail() {
        // space after 09 , before number 16        !                      !
       assertThrows(NumberFormatException.class,
               () -> new CalendarClass("09 :00-10:30,12:00-13:00, 16:00-18:00", "09:00-20:00"));
    }

    @Test
    public void testCalendarEquality() {
        assertEquals(calendar2, calendar1, "Not equal");
        assertEquals(calendar1.getCalendar(), calendar2.getCalendar(), "Not equal");
    }

    @Test
    public void testCalendarNonEquality() {
        assertNotEquals(calendar3, calendar1, "Not equal");
        assertNotEquals(calendar3.getCalendar(), calendar2.getCalendar(), "Not equal");
    }

    @Test
    public void testCalendarMerge() {
        CalendarClass result = new CalendarClass("10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00", "10:00-18:30");
        assertEquals(result.toString(), calendar3.mergeWithCalendar(calendar4).toString(),"Not equal");
    }

    @Test
    public void testCalendarGetPossibleEventsWith() {
        List<Integer[]> cal3 = calendar3.mergeWithCalendar(calendar4).getFreeTime();
        List<Integer[]> cal4 = calendar3.getPossibleEvents(calendar4, 30);
        assertArrayEquals(cal3.toArray(), cal4.toArray(), "Not equal");
    }

    @Test
    public void testCalendarGetFreeTimeWithParam() {
        CalendarClass cal1 = new CalendarClass("09:00-10:30,11:00-13:00,16:00-18:00",
                "09:00-20:00");

        assertEquals("[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", cal1.getPrettyTime(), "Not equal");
        assertEquals("[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", cal1.getPrettyTime(30), "Not equal");
        assertEquals("[[13:00, 16:00], [18:00, 20:00]]", cal1.getPrettyTime(60), "Not equal");
        assertEquals("[[13:00, 16:00]]", cal1.getPrettyTime(121), "Not equal");
        assertEquals("[]", cal1.getPrettyTime(181), "Not equal");
    }

}

