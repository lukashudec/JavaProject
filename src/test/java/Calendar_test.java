import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class Calendar_test {

    @Test
    void test_to_string() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})),
                new String[]{"09:00", "20:00"});
        String result = "[[12:00, 13:00], [16:00, 18:00]] / [09:00, 20:00]";
        assertEquals(result, cal1.toString(), "Not equal");
    }

    @Test
    void test_calendar_creation() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"09:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})), new String[]{"09:00", "20:00"});
        Calendar cal2 = new Calendar("09:00-10:30,12:00-13:00,16:00-18:00","09:00-20:00");
        assertEquals(cal1.toString(),cal2.toString(), "Not equal");
    }

    @Test
    void test_calendar_merge() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})),
                new String[]{"09:00", "20:00"});
        Calendar cal2 = new Calendar(new ArrayList<>(List.of
                (new String[]{"10:00", "11:45"}, new String[]{"12:30", "14:30"})),
                new String[]{"10:00", "18:30"});
        Calendar result = new Calendar(new ArrayList<>(List.of
                (new String[]{"10:00", "11:45"}, new String[]{"12:00", "13:00"},new String[]{"12:30", "14:30"}, new String[]{"16:00", "18:00"})),
                new String[]{"10:00", "18:30"});
        Calendar cal3 = cal1.mergeWithCalendar(cal2);
        assertEquals(cal3.toString(), result.toString(),"Not equal");
    }

    @Test
    void test_calendar_get_possible_events_with() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})),
                new String[]{"09:00", "20:00"});
        Calendar cal2 = new Calendar(new ArrayList<>(List.of
                (new String[]{"10:00", "11:45"}, new String[]{"12:30", "14:30"})),
                new String[]{"10:00", "18:30"});
        Calendar result = new Calendar(new ArrayList<>(List.of
                (new String[]{"10:00", "11:45"}, new String[]{"12:00", "13:00"},new String[]{"12:30", "14:30"}, new String[]{"16:00", "18:00"})),
                new String[]{"10:00", "18:30"});
        List<Integer[]> cal3 = cal1.mergeWithCalendar(cal2).getFreeTime();
        List<Integer[]> cal4 = cal1.getPossibleEvents(cal2, 30);
        assertArrayEquals(cal3.toArray(), cal4.toArray(),"Not equal");
    }

    @Test
    void test_calendar_get_free_time_with_param() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"09:00", "10:30"}, new String[]{"11:00", "13:00"}, new String[]{"16:00", "18:00"})),
                new String[]{"09:00", "20:00"});

        assertArrayEquals(cal1.getPrettyTime().toArray(),
                new Object[]{new String[]{"10:30", "11:00"}, new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        assertArrayEquals(cal1.getPrettyTime(30).toArray(),
                new Object[]{new String[]{"10:30", "11:00"}, new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        assertArrayEquals(cal1.getPrettyTime(60).toArray(),
                new Object[]{new String[]{"13:00", "16:00"}, new String[]{"18:00", "20:00"}},
                "Not equal");
        assertArrayEquals(cal1.getPrettyTime(121).toArray(),
                new Object[]{new String[]{"13:00", "16:00"}},
                "Not equal");
        assertArrayEquals(cal1.getPrettyTime(181).toArray(),
                new Object[]{},
                "Not equal");
    }
    
}

