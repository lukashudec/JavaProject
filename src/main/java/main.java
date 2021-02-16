import java.util.Arrays;

public class main {
    public static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
    Calendar cal = new Calendar("09:00-10:30,12:00-13:00,16:00-18:00","09:00-19:30");
    Calendar cal2 = new Calendar("13:00-14:00,18:00-19:00", "10:00-20:00");

    print(cal.toString());
    print(cal.getCalendar());
    print(Arrays.deepToString(cal.getFreeTime().toArray()));
    print(Arrays.deepToString(cal.getPrettyTime(30).toArray()));

    print(cal2.toString());
    print(cal2.getCalendar());

    print(cal.mergeWithCalendar(cal2).toString());
    print(Arrays.deepToString(cal.getPossibleEvents(cal2,30).toArray()));

    }
}
