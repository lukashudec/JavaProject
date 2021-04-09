package unitTests.utility;

import calendar.utility.ListCreator;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListCreatorTest {

    @Test
    void testInit() {
        List<Integer> l = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        l.add(10);

        List<Integer> result = new ListCreator<>(List.of(1,2,3,4,5,6,7,8,9)).add(10).get();
        assertEquals(l,result);
    }

    @Test
    void testInitList() {
        List<Integer> l = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        l.addAll(List.of(6,7,8,9));
        l.add(10);

        List<Integer> result = new ListCreator<>(List.of(1,2,3,4,5),List.of(6,7,8,9)).add(10).get();
        assertEquals(l,result);
    }

    @Test
    void testGet() {
        List<Integer> l = List.of(1,2,3,4,5,6,7,8,9);
        List<Integer> result = new ListCreator<>(List.of(1,2,3,4,5,6,7,8,9)).get();
        assertEquals(l,result);
    }

    @Test
    void testTrimming() {
        List<Integer> l = List.of(2,3,4,5,6,7,8);
        List<Integer> result = new ListCreator<>(List.of(1,2,3,4,5,6,7,8,9)).getTrimmed(1,1);
        assertEquals(l,result);
    }

    @Test
    void testSort() {
        List<Integer> l = List.of(2,3,4,5,6,7,8);
        List<Integer> result = new ListCreator<>(List.of(4,8,2,5,7,6,3)).sort(Comparator.comparing(Integer::intValue));
        assertEquals(l,result);
    }

}