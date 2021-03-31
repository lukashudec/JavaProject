package utility;

import java.util.ArrayList;
import java.util.List;

public class Events extends ArrayList<TimeTuple> {
    public Events(List<TimeTuple> events) {
        super(events);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toPrettyString() {
        StringBuilder prettyResult  = new StringBuilder();
        for (TimeTuple t : this) {
            prettyResult.append(t.strFrom())
                    .append("-")
                    .append(t.strTo())
                    .append(",");
        }
        return prettyResult.deleteCharAt(prettyResult.length()-1).toString();
    }

}
