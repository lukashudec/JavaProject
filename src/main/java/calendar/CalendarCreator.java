package calendar;

import calendar.utility.Events;
import calendar.utility.ListCreator;
import calendar.utility.TimeTuple;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarCreator {
    private static final int DEFAULT_MINIMAL_DURATION = 30;
    private static final int DEFAULT_MAXIMUM_DURATION = 1440;
    private final List<TimeTuple> events= new ArrayList<>();

    public CalendarCreator(String rawCalendar, String rawBounds) {
        // transform Strings to TimeTouples
        List<TimeTuple> rawEvents = Arrays.stream(rawCalendar.split(","))
                .map(TimeTuple::new)
                .collect(Collectors.toList());
        TimeTuple bounds = new TimeTuple(rawBounds);

        // couple bounds with rawEvents
        events.add(new TimeTuple("00:00-"+bounds.strFrom()));
        events.addAll(rawEvents);
        events.add(new TimeTuple(bounds.strTo() +"-24:00"));

    }

    private CalendarCreator(List<TimeTuple> calendar) {
        events.addAll(squishAndClean(calendar));
    }

    private List<TimeTuple> squishAndClean(List<TimeTuple> calendar) {
        for (int i = 0; i < calendar.size() - 1; i++) {
            TimeTuple tup = calendar.get(i).squish(calendar.get(i+1));
            if (tup!=null) {
                calendar.set(i,tup);
                calendar.remove(i+1);
                i--;
            }
        }
        return calendar;
    }

    public CalendarCreator mergeWith(CalendarCreator cal2) {
        // merge and sort calendars, remove first and last element
        return new CalendarCreator(
                new ListCreator<>(this.events,cal2.events)
                .sortWith(Comparator.comparing(TimeTuple::strFrom))
                .getTrimmed(1,1));
    }

    public Events getFreeTime() {
        return getFreeTime(DEFAULT_MINIMAL_DURATION);
    }

    public Events getFreeTime(int minDuration) {
        minDuration = Math.max(minDuration, DEFAULT_MINIMAL_DURATION);
        minDuration = Math.min(minDuration, DEFAULT_MAXIMUM_DURATION);

        List<TimeTuple> freeTime= new ArrayList<>();
        for (int i = 0; i < events.size() - 1; i++) {
            if (events.get(i + 1).intFrom() - events.get(i).intTo() >= minDuration) {
                freeTime.add(new TimeTuple(events.get(i).strTo(), events.get(i + 1).strFrom()));
            }
        }
        return new Events(freeTime);
    }

    public Events getFreeTime(CalendarCreator cal2) {
        return this.mergeWith(cal2).getFreeTime(DEFAULT_MINIMAL_DURATION);
    }

    public Events getFreeTime(CalendarCreator cal2, int minDuration) {
        return this.mergeWith(cal2).getFreeTime(minDuration);
    }

    public Events getEvents() {
        return new Events(events);
    }

}


