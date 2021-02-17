import java.util.*;
import java.util.ArrayList;

public class Calendar {
    List<String[]> rawCalendar;
    String[] bounds;
    List<Integer[]> calendar;

    public Calendar(List<String[]> rawCalendar, String[] bounds) {
        this.rawCalendar = rawCalendar;
        this.bounds = bounds;
        this.calendar = formatInput();
    }

    public Calendar(String rawCalendar, String bounds) {
        this(getTime(rawCalendar),getTime(bounds).get(0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar2 = (Calendar) o;
        return this.hashCode()==calendar2.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.rawCalendar.toArray()) + " / " + Arrays.toString(this.bounds);
    }

    public String getCalendar() {
        return Arrays.deepToString(calendar.toArray());
    }

    public Calendar mergeWithCalendar(Calendar cal2) {
        List<String[]> newCalendar = this.rawCalendar;
        String[] bounds = this.bounds;
        newCalendar.addAll(cal2.rawCalendar);
        String[] newBounds = {comp(bounds[0], cal2.bounds[0], 1), comp(bounds[1], cal2.bounds[1], -1)};
        newCalendar.sort(Comparator.comparing(o -> ((String[]) o)[0]));
        return new Calendar(newCalendar, newBounds);
    }

    public List<Integer[]> formatInput() {
        List<String[]> calendar = new ArrayList<>();
        List<Integer[]> result = new ArrayList<>();
        calendar.add(new String[]{"00:00", bounds[0]});
        calendar.addAll(rawCalendar);
        calendar.add(new String[]{bounds[1], "00:00"});
        for (String[] s : calendar) {
            String[] timeFrom = s[0].split(":");
            String[] timeTo = s[1].split(":");
            result.add(new Integer[]{Integer.parseInt(timeFrom[0]) * 60 + Integer.parseInt(timeFrom[1]),
                    Integer.parseInt(timeTo[0]) * 60 + Integer.parseInt(timeTo[1])});
        }
        return result;
    }

    public List<Integer[]> getFreeTime() {
        return getFreeTime(30);
    }

    public List<Integer[]> getFreeTime(int minDuration) {
        List<Integer[]> result = new ArrayList<>();
        for (int i = 0; i < calendar.size() - 1; i++) {
            Integer[] event = calendar.get(i);
            Integer[] nextEvent = calendar.get(i + 1);
            if (nextEvent[0] - event[1] >= minDuration) {
                result.add(new Integer[]{event[1], nextEvent[0]});
            }
        }
        return result;
    }

    public List<Integer[]> getPossibleEvents(Calendar cal2, int minDuration) {
        return this.mergeWithCalendar(cal2).getFreeTime(minDuration);
    }

    public List<String[]> getPrettyTime() {
        return getPrettyTime(30);
    }

    public List<String[]> getPrettyTime(int minDuration) {
        List<String[]> result = new ArrayList<>();
        for (Integer[] i : this.getFreeTime(minDuration)) {
            result.add(new String[]{formatTime(i[0]/60)+ ':' + formatTime(i[0]% 60),
                    formatTime(i[1]/60)+ ':' + formatTime(i[1]% 60)});
        }
        return result;
    }

    public static List<String[]> getTime(String stringTime) {
        ArrayList<String[]> result = new ArrayList<>();
        for (String time_frame : stringTime.split(",")) {
            result.add(time_frame.split("-"));
        }
        return result;
    }

    public static String formatTime(int time) {
        String timeString = String.valueOf(time);
        return (timeString.length()==1) ? '0'+timeString : timeString;
    }

    public static String comp(String a, String b, int i) {
        return (a.compareTo(b) == i) ? a : b;
    }
}


