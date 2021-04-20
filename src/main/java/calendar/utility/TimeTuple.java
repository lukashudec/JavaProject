package calendar.utility;

import java.util.Objects;

public class TimeTuple {
    private String strFrom;
    private int intFrom;
    private String strTo;
    private int intTo;

    public TimeTuple(String s) {
        this(s.split("-")[0],s.split("-")[1]);
    }

    public TimeTuple(String from, String to) {
        this.strFrom = from;
        intFrom = convertToInt(from);
        this.strTo = to;
        intTo = convertToInt(to);
    }

    private int convertToInt(String s) {
        return Integer.parseInt(s.split(":")[0])*60 + Integer.parseInt(s.split(":")[1]);
    }

    public void setFrom(String from) {
        this.strFrom = from;
        intFrom = convertToInt(from);
    }

    public void setTo(String to) {
        this.strTo = to;
        intTo = convertToInt(to);
    }


    public static TimeTuple[] sortFrom(TimeTuple one, TimeTuple two) {
        if (one.intFrom <=two.intFrom)
            return new TimeTuple[]{one,two};
        return new TimeTuple[]{two,one};
    }

    public static TimeTuple[] sortTo(TimeTuple one, TimeTuple two) {
        if (one.intTo <=two.intTo)
                return new TimeTuple[]{one,two};
        return new TimeTuple[]{two,one};
    }

    public TimeTuple squish(TimeTuple t) {
        TimeTuple first = sortFrom(this,t)[0];
        TimeTuple second = sortFrom(this,t)[1];

        if (second.intFrom>=first.intFrom && second.intFrom<=first.intTo) {
            return new TimeTuple(first.strFrom,sortTo(first,second)[1].strTo);
        }

        return null;
    }

    @Override
    public String toString() {
        return intFrom +"-"+ intTo;
    }

    public String strFrom() {
        return strFrom;
    }

    public int intFrom() {
        return intFrom;
    }

    public String strTo() { return strTo; }

    public int intTo() {
        return intTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeTuple timeTuple = (TimeTuple) o;
        return strFrom.equals(timeTuple.strFrom) && strTo.equals(timeTuple.strTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strFrom, strTo);
    }
}
