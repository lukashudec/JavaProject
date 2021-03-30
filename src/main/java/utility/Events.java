package utility;

import java.util.List;

public class Events{
    private final List<TimeTuple> eventList;

    public Events(List<TimeTuple> events) {
        this.eventList=events;
    }

    @Override
    public String toString() {
        return this.eventList.toString();
    }

    public String toPrettyString() {
        StringBuilder prettyResult  = new StringBuilder();
        for (TimeTuple t : eventList) {
            prettyResult.append(t.strFrom())
                    .append("-")
                    .append(t.strTo())
                    .append(",");
        }
        return prettyResult.deleteCharAt(prettyResult.length()-1).toString();
    }
}
