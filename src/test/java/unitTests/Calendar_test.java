package unitTests;

import Calendar.Calendar;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Calendar_test {
    Calendar calendar1 = new Calendar(new ArrayList<>(List.of
            (new String[]{"09:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})), new String[]{"09:00", "20:00"});
    Calendar calendar2 = new Calendar("09:00-10:30,12:00-13:00,16:00-18:00", "09:00-20:00");
    Calendar calendar3 = new Calendar("12:00-13:00,16:00-18:00", "09:00-20:00");
    Calendar calendar4 = new Calendar("10:00-11:45,12:30-14:30", "10:00-18:30");

    @Test
    void test_toString() {
        String result = "[[09:00, 10:30], [12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00]";
        assertEquals(result, calendar1.toString(), "Not equal");
    }

    @Test
    void test_calendarCreation() {
        assertEquals(calendar1.toString(), calendar2.toString(), "Not equal");
    }

    @Test
    void test_calendarEquality() {
        assertEquals(calendar2, calendar1, "Not equal");
        assertEquals(calendar1.getCalendar(), calendar2.getCalendar(), "Not equal");
    }

    @Test
    void test_calendarMerge() {
        Calendar result = new Calendar("10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00", "10:00-18:30");
        assertEquals(calendar3.mergeWithCalendar(calendar4).toString(), result.toString(), "Not equal");
    }

    @Test
    void test_calendarGetPossibleEventsWith() {
        List<Integer[]> cal3 = calendar3.mergeWithCalendar(calendar4).getFreeTime();
        List<Integer[]> cal4 = calendar3.getPossibleEvents(calendar4, 30);
        assertArrayEquals(cal3.toArray(), cal4.toArray(), "Not equal");
    }

    @Test
    void test_calendarGetFreeTimeWithParam() {
        Calendar cal1 = new Calendar("09:00-10:30,11:00-13:00,16:00-18:00",
                "09:00-20:00");

        assertEquals(cal1.getPrettyTime(),
                "[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", "Not equal");
        assertEquals(cal1.getPrettyTime(30),
                "[[10:30, 11:00], [13:00, 16:00], [18:00, 20:00]]", "Not equal");
        assertEquals(cal1.getPrettyTime(60),
                "[[13:00, 16:00], [18:00, 20:00]]", "Not equal");
        assertEquals(cal1.getPrettyTime(121),
                "[[13:00, 16:00]]", "Not equal");
        assertEquals(cal1.getPrettyTime(181),
                "[]", "Not equal");
    }

}

