package calendar.utility;

import java.util.Objects;

public class TimeTuple {
    private String fromStr;
    private int fromInt;
    private String toStr;
    private int toInt;

    public TimeTuple(String s) {
        this(s.split("-")[0],s.split("-")[1]);
    }

    public TimeTuple(String from, String to) {
        this.fromStr = from;
        fromInt = convertToInt(from);
        this.toStr = to;
        toInt = convertToInt(to);
    }

    private int convertToInt(String s) {
        return Integer.parseInt(s.split(":")[0])*60 + Integer.parseInt(s.split(":")[1]);
    }

    public void setFrom(String from) {
        this.fromStr = from;
        fromInt = convertToInt(from);
    }

    public void setTo(String to) {
        this.toStr = to;
        toInt = convertToInt(to);
    }

    public static TimeTuple[] sort(TimeTuple one, TimeTuple two) {
        if (one.fromInt<=two.fromInt) {
            return new TimeTuple[]{one,two};
        } else return new TimeTuple[]{two,one};
    }

    public TimeTuple squish(TimeTuple t) {
        TimeTuple first = sort(this,t)[0];
        TimeTuple second = sort(this,t)[1];

        if (first.toInt>=second.toInt) {
            return first;
        } else if(first.toInt>=second.fromInt) {
            first.setTo(second.toStr);
            return first;
        }
        return null;
    }

    @Override
    public String toString() {
        return fromInt +"-"+ toInt;
    }

    public String strFrom() {
        return fromStr;
    }

    public int intFrom() {
        return fromInt;
    }

    public String strTo() { return toStr; }

    public int intTo() {
        return toInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTuple timeTuple = (TimeTuple) o;
        return fromStr.equals(timeTuple.fromStr) && toStr.equals(timeTuple.toStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromStr, toStr);
    }
}
