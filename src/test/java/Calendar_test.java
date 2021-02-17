import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class Calendar_test {
    
    @Test
    void test_calendar_creation() {
        Calendar cal1 = new Calendar(new ArrayList<>(List.of
                (new String[]{"09:00", "10:30"}, new String[]{"12:00", "13:00"}, new String[]{"16:00", "18:00"})), new String[]{"09:00", "20:00"});
        Calendar cal2 = new Calendar("09:00-10:30,12:00-13:00,16:00-18:00","09:00-20:00");
        assertEquals(cal1.toString(),cal2.toString(), "Not equal");
    }

    @Test
    void test_calendar_merge() {

    }
    
}

