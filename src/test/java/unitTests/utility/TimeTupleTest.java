package unitTests.utility;

import calendar.utility.TimeTuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTupleTest {
    TimeTuple t1 = new TimeTuple("12:00-13:00");
    TimeTuple t2 = new TimeTuple("12:00","13:00");
    TimeTuple t3 = new TimeTuple("14:00","15:00");

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
    void testStrFrom() {
        assertEquals("12:00",t1.strFrom());
    }

    @Test
    void testIntFrom() {
        assertEquals(720,t1.intFrom());
    }

    @Test
    void testStrTo() {
        assertEquals("13:00",t1.strTo());
    }

    @Test
    void testIntTo() {
        assertEquals(780,t1.intTo());
    }

    @Test
    void testSortFrom() {
        assertEquals(t1,TimeTuple.sort(t3,t1,"FROM")[0]);
        assertEquals(t3,TimeTuple.sort(t3,t1,"FROM")[1]);
    }

    @Test
    void testSortTo() {
        assertEquals(t1,TimeTuple.sort(t3,t1,"TO")[0]);
        assertEquals(t3,TimeTuple.sort(t3,t1,"TO")[1]);
    }

    @Test
    void testSquishBorder() {
        TimeTuple t1 = new TimeTuple("12:00-14:00");
        TimeTuple t2 = new TimeTuple("14:00","15:00");
        assertEquals(new TimeTuple("12:00","15:00"),t1.squish(t2));

    }

    @Test
    void testSquishOverlap() {
        TimeTuple t1 = new TimeTuple("12:00-14:30");
        TimeTuple t2 = new TimeTuple("14:00","15:00");
        assertEquals(new TimeTuple("12:00","15:00"),t1.squish(t2));
    }

    @Test
    void testSquishInside() {
        TimeTuple t1 = new TimeTuple("12:00-15:00");
        TimeTuple t2 = new TimeTuple("13:00","14:00");
        assertEquals(new TimeTuple("12:00","15:00"),t1.squish(t2));
    }



}