package calendar;

import utility.Events;
import utility.ListCreator;
import utility.TimeTuple;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarCreator {
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
        events.add(new TimeTuple(bounds.strTo() +"-00:00"));

    }

    private CalendarCreator(List<TimeTuple> calendar) {
        events.addAll(calendar);
    }

    public CalendarCreator mergeWith(CalendarCreator cal2) {
        // merge and sort calendars, remove first and last element
        return new CalendarCreator(
                new ListCreator<>(this.events,cal2.events)
                .sortWith(Comparator.comparing(TimeTuple::strFrom))
                .getTrimmed(1,1));
    }

    public Events getFreeTime() {
        return getFreeTime(30);
    }

    public Events getFreeTime(int minDuration) {
        List<TimeTuple> freeTime= new ArrayList<>();
        for (int i = 0; i < events.size() - 1; i++) {
            if (events.get(i + 1).intFrom() - events.get(i).intTo() >= minDuration) {
                freeTime.add(new TimeTuple(events.get(i).strFrom(), events.get(i + 1).strTo()));
            }
        }
        return new Events(freeTime);
    }

    public Events getFreeTime(CalendarCreator cal2) {
        return this.mergeWith(cal2).getFreeTime(30);
    }

    public Events getFreeTime(CalendarCreator cal2, int minDuration) {
        return this.mergeWith(cal2).getFreeTime(minDuration);
    }

    public Events getEvents() {
        return new Events(events);
    }

}


