package utility;

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

    public String strTo() {
        return toStr;
    }

    public int intTo() {
        return toInt;
    }
}
