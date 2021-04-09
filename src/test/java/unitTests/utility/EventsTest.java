package unitTests.utility;

import calendar.utility.Events;
import calendar.utility.TimeTuple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {
    static List<TimeTuple> listTime = new ArrayList<>();

    @BeforeAll
    static void populate() {
        listTime.add(new TimeTuple("12:00-13:00"));
        listTime.add(new TimeTuple("16:00-18:00"));
    }

    @Test
    void testToString() {
        Events e = new Events(listTime);
        assertEquals("[720-780, 960-1080]",e.toString(),"Not equal");
    }

    @Test
    void toPrettyString() {
        Events e = new Events(listTime);
        assertEquals("12:00-13:00,16:00-18:00",e.toPrettyString(),"Not equal");
    }
}