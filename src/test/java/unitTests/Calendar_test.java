package unitTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import Calendar.Calendar;

public class Calendar_test {
    Calendar calendar1 = new Calendar(new ArrayList<>(List.of
            (new String[]{"09:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})), new String[]{"09:00", "20:00"});
    Calendar calendar2 = new Calendar("09:00-10:30,12:00-13:00,16:00-18:00","09:00-20:00");
    Calendar calendar3 = new Calendar("12:00-13:00,16:00-18:00","09:00-20:00");
    Calendar calendar4 = new Calendar("10:00-11:45,12:30-14:30","10:00-18:30");

    @Test
    void test_toString() {
        String result = "[[09:00, 10:30], [12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00]";
        Assertions.assertEquals(result, calendar1.toString(), "Not equal");
    }

    @Test
    void test_calendarCreation() {
        Assertions.assertEquals(calendar1.toString(),calendar2.toString(), "Not equal");
    }

    @Test
    void test_calendarEquality() {
        Assertions.assertEquals(calendar2, calendar1, "Not equal");
        Assertions.assertEquals(calendar1.getCalendar(), calendar2.getCalendar(), "Not equal" );
    }

    @Test
    void test_calendarMerge() {
        Calendar result = new Calendar("10:00-11:45,12:00-13:00,12:30-14:30,16:00-18:00","10:00-18:30");
        Assertions.assertEquals(calendar3.mergeWithCalendar(calendar4).toString(), result.toString(),"Not equal");
    }

    @Test
    void test_calendarGetPossibleEventsWith() {
        List<Integer[]> cal3 = calendar3.mergeWithCalendar(calendar4).getFreeTime();
        List<Integer[]> cal4 = calendar3.getPossibleEvents(calendar4, 30);
        assertArrayEquals(cal3.toArray(), cal4.toArray(),"Not equal");
    }

    @Test
    void test_calendarGetFreeTimeWithParam() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"09:00", "10:30"}, new String[]{"11:00", "13:00"}, new String[]{"16:00", "18:00"})),
                new String[]{"09:00", "20:00"});

        Assertions.assertArrayEquals(cal1.getPrettyTime().toArray(),
                new Object[]{new String[]{"10:30", "11:00"}, new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        Assertions.assertArrayEquals(cal1.getPrettyTime(30).toArray(),
                new Object[]{new String[]{"10:30", "11:00"}, new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        Assertions.assertArrayEquals(cal1.getPrettyTime(60).toArray(),
                new Object[]{new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        Assertions.assertArrayEquals(cal1.getPrettyTime(121).toArray(),
                new Object[]{new String[]{"13:00", "16:00"}},
                "Not equal");
        Assertions.assertArrayEquals(cal1.getPrettyTime(181).toArray(),
                new Object[]{},
                "Not equal");
    }
    
}

