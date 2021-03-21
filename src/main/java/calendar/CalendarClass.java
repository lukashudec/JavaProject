package calendar;

import java.util.*;

public class CalendarClass {
    List<String[]> rawCalendar;
    String[] bounds;
    List<Integer[]> calendar;

    public CalendarClass(List<String[]> rawCalendar, String[] bounds) {
        this.rawCalendar = rawCalendar;
        this.bounds = bounds;
        this.calendar = formatInput();
    }

    public CalendarClass(String rawCalendar, String bounds) {
        this(getTime(rawCalendar), getTime(bounds).get(0));
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
        return (timeString.length() == 1) ? '0' + timeString : timeString;
    }

    public static String comp(String a, String b, int i) {
        return (a.compareTo(b) == i) ? a : b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarClass calendar2 = (CalendarClass) o;
        return this.hashCode() == calendar2.hashCode();
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

    public CalendarClass mergeWithCalendar(CalendarClass cal2) {
        List<String[]> newCalendar = this.rawCalendar;
        newCalendar.addAll(cal2.rawCalendar);
        String[] newBounds = {comp(bounds[0], cal2.bounds[0], 1), comp(bounds[1], cal2.bounds[1], -1)};
        newCalendar.sort(Comparator.comparing(o -> (o)[0]));
        return new CalendarClass(newCalendar, newBounds);
    }

    public List<Integer[]> formatInput() {
        List<String[]> tempCalendar = new ArrayList<>();
        List<Integer[]> result = new ArrayList<>();
        tempCalendar.add(new String[]{"00:00", bounds[0]});
        tempCalendar.addAll(rawCalendar);
        tempCalendar.add(new String[]{bounds[1], "00:00"});
        for (String[] s : tempCalendar) {
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

    public List<Integer[]> getPossibleEvents(CalendarClass cal2, int minDuration) {
        return this.mergeWithCalendar(cal2).getFreeTime(minDuration);
    }

    public String getPrettyTime() {
        return getPrettyTime(30);
    }

    public String getPrettyTime(int minDuration) {
        List<String[]> result = new ArrayList<>();
        for (Integer[] i : this.getFreeTime(minDuration)) {
            result.add(new String[]{formatTime(i[0] / 60) + ':' + formatTime(i[0] % 60),
                    formatTime(i[1] / 60) + ':' + formatTime(i[1] % 60)});
        }
        return Arrays.deepToString(result.toArray());
    }
}


