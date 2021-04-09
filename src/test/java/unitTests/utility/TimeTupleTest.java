package unitTests.utility;

import calendar.utility.TimeTuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTupleTest {
    TimeTuple t1 = new TimeTuple("12:00-13:00");
    TimeTuple t2 = new TimeTuple("12:00","13:00");

    @Test
    void testEquals() {
        assertEquals(t1,t2);
    }

    @Test
    void testContentEquals() {
        assertEquals(t1.toString(),t2.toString());
    }

    @Test
    void testToString() {
        assertEquals("720-780",t1.toString());
    }

    @Test
    void strFrom() {
        assertEquals("12:00",t1.strFrom());
    }

    @Test
    void intFrom() {
        assertEquals(720,t1.intFrom());
    }

    @Test
    void strTo() {
        assertEquals("13:00",t1.strTo());
    }

    @Test
    void intTo() {
        assertEquals(780,t1.intTo());
    }

    @Test
    void sort() {
        TimeTuple t1 = new TimeTuple("12:00-13:00");
        TimeTuple t2 = new TimeTuple("14:00","15:00");

        assertEquals(t1,TimeTuple.sort(t2,t1)[0]);
        assertEquals(t2,TimeTuple.sort(t2,t1)[1]);
    }


}